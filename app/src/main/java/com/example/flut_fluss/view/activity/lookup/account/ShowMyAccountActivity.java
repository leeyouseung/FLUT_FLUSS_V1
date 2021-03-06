package com.example.flut_fluss.view.activity.lookup.account;

import android.content.Intent;

import android.os.Bundle;

import com.example.flut_fluss.R;
import com.example.flut_fluss.base.BaseActivity;
import com.example.flut_fluss.databinding.ShowMyAccountListActivityBinding;
import com.example.flut_fluss.view.activity.lookup.account.remittance.LookupSendMoneyActivity;
import com.example.flut_fluss.viewmodel.ShowMyAccountListViewModel;

public class ShowMyAccountActivity extends BaseActivity<ShowMyAccountListActivityBinding, ShowMyAccountListViewModel> {

    @Override
    protected int getLayoutId() {

        return R.layout.show_my_account_list_activity;
    }

    @Override
    protected Class<ShowMyAccountListViewModel> getViewModel() {

        return ShowMyAccountListViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        event();
    }

    private void initData() {

        Intent intent = getIntent();

        binding.accountKindTitle.setText(intent.getStringExtra("title_money_list"));
        binding.accountMoney.setText(intent.getStringExtra("account_money"));
    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {

        clickBackButton();
        clickAddMoneyImgButton();
        clickAddMoneyTextButton();
    }

    private void clickBackButton() {

        binding.backButton.setOnClickListener(v -> {

            finish();
        });
    }

    private void clickAddMoneyImgButton() {

        binding.addMoneyButton.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), LookupSendMoneyActivity.class);

            intent.putExtra("big_title", "충전");
            intent.putExtra("title_kind", binding.accountKindTitle.getText().toString());
            intent.putExtra("account_money", binding.accountMoney.getText().toString());
            intent.putExtra("button_text", "충전하기");

            startActivity(intent);
        });
    }

    private void clickAddMoneyTextButton() {

        binding.addMoneyButtonText.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), LookupSendMoneyActivity.class);

            intent.putExtra("big_title", "충전");
            intent.putExtra("title_kind", binding.accountKindTitle.getText().toString());
            intent.putExtra("account_money", binding.accountMoney.getText().toString());
            intent.putExtra("button_text", "충전하기");

            startActivity(intent);
        });
    }
}
