package com.example.flut_fluss.view.activity.inner_timline;

import android.os.Bundle;

import com.example.flut_fluss.R;
import com.example.flut_fluss.base.BaseActivity;
import com.example.flut_fluss.databinding.EtcActivityBinding;

public class EtcActivity extends BaseActivity<EtcActivityBinding> {

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

    }

    @Override
    protected int layoutId() {

        return R.layout.etc_activity;
    }
}