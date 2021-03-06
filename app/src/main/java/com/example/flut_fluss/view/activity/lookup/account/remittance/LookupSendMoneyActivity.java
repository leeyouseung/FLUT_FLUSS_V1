package com.example.flut_fluss.view.activity.lookup.account.remittance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.flut_fluss.R;
import com.example.flut_fluss.base.BaseActivity;
import com.example.flut_fluss.databinding.LookupSendMoneyActivityBinding;
import com.example.flut_fluss.viewmodel.LookupSendMoneyViewModel;

public class LookupSendMoneyActivity extends BaseActivity<LookupSendMoneyActivityBinding, LookupSendMoneyViewModel> {

    private Animation anim = new AlphaAnimation(0.0f, 1.0f);

    final private String CHECK_MONEY = "잔액 : 0 원";

    public String sendMoney = "0";

    private boolean isFirstInput = true;

    @Override
    protected int getLayoutId() {

        return R.layout.lookup_send_money_activity;
    }

    @Override
    protected Class<LookupSendMoneyViewModel> getViewModel() {

        return LookupSendMoneyViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        initSetBlinkAnimation();

        event();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {

        binding.money.setText(String.valueOf(0));

        Intent intent = getIntent();

        String bigTitle = intent.getStringExtra("big_title");

        if(bigTitle == null) {

            binding.accountKindTitle.setText(intent.getStringExtra("title_money_list"));
            binding.accountMoney.setText("잔액 : " + intent.getStringExtra("account_money"));
            binding.remittanceButton.setText(intent.getStringExtra("button_text"));
        }
        else {

            binding.bigTitle.setText(intent.getStringExtra("big_title"));
            binding.accountKindTitle.setText(intent.getStringExtra("title_kind"));
            binding.accountMoney.setText("잔액 : " + intent.getStringExtra("account_money"));
            binding.remittanceButton.setText(intent.getStringExtra("button_text"));
        }
    }

    private void initSetBlinkAnimation() {

        anim.setDuration(50); // You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(5); // blink time
    }

    private void event() {

        clickEvent();
    }

    private void visibleDeleteCancelButton() {

        binding.deleteButton.setVisibility(View.VISIBLE);
        binding.cancelButton.setVisibility(View.VISIBLE);
    }

    private void invisibleDeleteCancelButton() {

        binding.deleteButton.setVisibility(View.INVISIBLE);
        binding.cancelButton.setVisibility(View.INVISIBLE);
    }

    private void checkBlinkMoneySet() {

        binding.accountMoney.startAnimation(anim);
    }

    private void sendButtonOff() {

        binding.remittanceButton.setBackground(getResources().getDrawable(R.drawable.money_send_button_off_radius));
        binding.remittanceButton.setClickable(false);
    }

    private void sendButtonOn() {

        binding.remittanceButton.setBackground(getResources().getDrawable(R.drawable.money_send_button_radius));
        binding.remittanceButton.setClickable(true);

        clickRemittanceButton();
    }

    private void clickEvent() {

        clickBackButton();
        clickMoneyNumber0();
        clickMoneyNumber1();
        clickMoneyNumber2();
        clickMoneyNumber3();
        clickMoneyNumber4();
        clickMoneyNumber5();
        clickMoneyNumber6();
        clickMoneyNumber7();
        clickMoneyNumber8();
        clickMoneyNumber9();
        clickCancelButton();
        clickDeleteButton();
    }

    private void clickBackButton() {

        binding.backButton.setOnClickListener(v -> {

            finish();
        });
    }

    private void clickMoneyNumber0() {

        binding.moneyNumber0.setOnClickListener(v -> {

            if (isFirstInput && binding.money.getText().toString().equals("0")) {

                sendButtonOff();
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(0));

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber1() {

        binding.moneyNumber1.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(1));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(1));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber2() {

        binding.moneyNumber2.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(2));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(2));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber3() {

        binding.moneyNumber3.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(3));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(3));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber4() {

        binding.moneyNumber4.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(4));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(4));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber5() {

        binding.moneyNumber5.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(5));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(5));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber6() {

        binding.moneyNumber6.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(6));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(6));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber7() {

        binding.moneyNumber7.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(7));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(7));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber8() {

        binding.moneyNumber8.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(8));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(8));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickMoneyNumber9() {

        binding.moneyNumber9.setOnClickListener(v -> {

            if (isFirstInput) {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.setText(String.valueOf(9));

                    isFirstInput = false;

                    visibleDeleteCancelButton();

                    setMoveMoneyCountTitle();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
            else {

                if(binding.accountMoney.getText().toString().equals(CHECK_MONEY)) {

                    checkBlinkMoneySet();

                    sendButtonOff();
                }
                else {

                    binding.money.append(String.valueOf(9));

                    visibleDeleteCancelButton();

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickCancelButton() {

        binding.cancelButton.setOnClickListener(v -> {

            binding.money.setText(String.valueOf(0));

            isFirstInput = true;

            invisibleDeleteCancelButton();

            resetMoveMoneyCountTitle();

            sendButtonOff();
        });
    }

    private void clickDeleteButton() {

        binding.deleteButton.setOnClickListener(v -> {

            if (isFirstInput) {

                isFirstInput = true;

                invisibleDeleteCancelButton();

                resetMoveMoneyCountTitle();

                sendButtonOff();
            }
            else {

                if (binding.money.getText().toString().length() == 1 && binding.money.getText().toString().equals("0")) {

                    binding.money.setText(String.valueOf(0));

                    isFirstInput = true;

                    invisibleDeleteCancelButton();

                    resetMoveMoneyCountTitle();

                    sendButtonOn();
                }
                else if (binding.money.getText().toString().length() == 1 && !(binding.money.getText().toString().equals("0"))) {

                    binding.money.setText(String.valueOf(0));

                    isFirstInput = true;

                    invisibleDeleteCancelButton();

                    resetMoveMoneyCountTitle();

                    sendButtonOn();
                }
                else {

                    String moneySet = binding.money.getText().toString().substring(0, binding.money.getText().toString().length() - 1);

                    binding.money.setText(moneySet);

                    isFirstInput = false;

                    sendMoney = binding.money.getText().toString();

                    sendButtonOn();
                }
            }
        });
    }

    private void clickRemittanceButton() {

        binding.remittanceButton.setOnClickListener(v -> {

            Toast.makeText(getApplicationContext(), "아직 서버 구현 X" ,Toast.LENGTH_SHORT).show();
        });
    }

    private void setMoveMoneyCountTitle() {

        binding.money.setX(binding.money.getX() + 42);
        binding.moneyCount.setX(binding.moneyCount.getX() + 42);
    }

    private void resetMoveMoneyCountTitle() {

        binding.money.setX(binding.money.getX() - 42);
        binding.moneyCount.setX(binding.moneyCount.getX() - 42);
    }
}
