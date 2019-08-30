package com.example.flut_fluss.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Toast;

import com.example.flut_fluss.R;
import com.example.flut_fluss.base.BaseActivity;
import com.example.flut_fluss.databinding.LoginActivityBinding;
import com.example.flut_fluss.view.activity.main.MainActivity;
import com.example.flut_fluss.view.activity.register.RegisterActivity;

public class LoginActivity extends BaseActivity<LoginActivityBinding> {

    private boolean checkBlind = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        event();
    }

    private void initData() {

    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {

        clickPasswordClearButton();
        clickBlindButton();
        clickLoginButton();
    }

    private void clickPasswordClearButton() {

        binding.pwClearButton.setOnClickListener(v -> binding.passwordText.setText(""));
    }

    private void clickBlindButton() {

        binding.blindButton.setOnClickListener(v -> {

            if (checkBlind) {

                binding.blindButton.setImageResource(R.drawable.ic_blind);
                binding.passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());

                checkBlind = false;
            }
            else {

                binding.blindButton.setImageResource(R.drawable.ic_blind_check);
                binding.passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                checkBlind = true;
            }
        });
    }

    private void clickLoginButton() {

        binding.loginButton.setOnClickListener(v -> {

            if (binding.idText.getText().toString().isEmpty()) {

                Toast.makeText(this,"아이디를 입력해주세요",Toast.LENGTH_SHORT).show();

                return;
            }
            if (binding.passwordText.getText().toString().isEmpty()) {

                Toast.makeText(this,"비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show();

                return;
            }
//            loginViewModel.login(
//                    new LoginRequest(binding.idText.getText().toString(),
//                            binding.passwordText.getText().toString()));

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        binding.registerButton.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });
    }

    @Override
    protected int layoutId() {

        return R.layout.login_activity;
    }
}
