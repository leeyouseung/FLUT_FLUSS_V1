package com.example.flut_fluss.view.activity.account_list.my_account.send_money_finger;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;

import androidx.core.content.ContextCompat;

import com.example.flut_fluss.R;
import com.example.flut_fluss.base.BaseActivity;
import com.example.flut_fluss.databinding.SendCheckFingerPrintActivityBinding;
import com.example.flut_fluss.manager.finger.SendCheckFingerprintHandler;
import com.example.flut_fluss.view.activity.account_list.my_account.send_money_password.SendCheckPasswordActivity;
import com.example.flut_fluss.viewmodel.SendCheckFingerPrintViewModel;
import com.example.flut_fluss.viewmodel.SendMyAccountViewModel;
import com.example.flut_fluss.viewmodel.SendMyBankViewModel;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SendCheckFingerPrintActivity extends BaseActivity<SendCheckFingerPrintActivityBinding, SendCheckFingerPrintViewModel> {

    private SendMyAccountViewModel sendMyAccountViewModel;
    private SendMyBankViewModel sendMyBankViewModel;

    private KeyStore keyStore;
    private Cipher cipher;
    private static final String KEY_NAME = "example_key";
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyGenerator keyGenerator = null;

    private String sendKind = "";
    private String money = "";

    @Override
    protected int getLayoutId() {
        return R.layout.send_check_finger_print_activity;
    }

    @Override
    protected Class getViewModel() {
        return SendCheckFingerPrintViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {   // Manifest 에 Fingerprint 퍼미션을 추가해 워야 사용가능

                binding.tvMessage.setText("지문을 사용할 수 없는 디바이스 입니다.");
            }
            else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

                binding.tvMessage.setText("지문사용을 허용해 주세요.");
                /*잠금화면 상태를 체크한다.*/
            }
            else if (!keyguardManager.isKeyguardSecure()) {

                binding.tvMessage.setText("잠금화면을 설정해 주세요.");
            }
            else if (!fingerprintManager.hasEnrolledFingerprints()) {

                binding.tvMessage.setText("등록된 지문이 없습니다.");
            }
            else {  //모든 관문을 성공적으로 통과(지문인식을 지원하고 지문 사용이 허용되어 있고 잠금화면이 설정되었고 지문이 등록되어 있을때)

                binding.tvMessage.setText("손가락을 홈버튼에 대 주세요.");

                generateKey();

                if(cipherInit()) {

                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);

                    SendCheckFingerprintHandler handler = new SendCheckFingerprintHandler(this);

                    handler.startAuthentication(fingerprintManager, cryptoObject);
                }
            }
        }

        event();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {

        Intent intent = getIntent();

        sendKind = intent.getStringExtra("send_kind");
        money = intent.getStringExtra("money");

        binding.money.setText(money + "원");

        if(sendKind.equals("0")) {

            binding.animationLogo.setImageResource(R.drawable.flut_logo);
        }
        else if(sendKind.equals("1")) {

            binding.animationLogo.setImageResource(R.drawable.bank_icon_white);
        }
    }

    //Cipher Init()
    public boolean cipherInit() {

        try {

            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {

            throw new RuntimeException("Failed to get Cipher", e);
        }
        try {

            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return true;

        } catch (KeyPermanentlyInvalidatedException e) {

            return false;

        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {

            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    //Key Generator
    protected void generateKey() {

        try {

            keyStore = KeyStore.getInstance("AndroidKeyStore");

        } catch (KeyStoreException e) {

            e.printStackTrace();
        }
        try {

            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {

            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }

        try {

            keyStore.load(null);

            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());

            keyGenerator.generateKey();

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | CertificateException | IOException e) {

            throw new RuntimeException(e);
        }
    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {

        clickCancelButton();
    }

    private void clickCancelButton() {

        binding.cancelButton.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), SendCheckPasswordActivity.class);

            intent.putExtra("send_kind", sendKind);
            intent.putExtra("money", money);

            startActivity(intent);
        });
    }
}
