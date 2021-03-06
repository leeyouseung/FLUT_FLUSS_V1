package com.example.flut_fluss.view.z_fragment.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.flut_fluss.R;
import com.example.flut_fluss.base.BaseFragment;
import com.example.flut_fluss.databinding.TimeLineFragmentBinding;
import com.example.flut_fluss.view.z_fragment.timeline.AnotherFragment;
import com.example.flut_fluss.view.z_fragment.timeline.ExpenditureFragment;
import com.example.flut_fluss.view.z_fragment.timeline.IncomeFragment;

public class TimeLineFragment extends BaseFragment<TimeLineFragmentBinding> {

    @Override
    protected int layoutId() {

        return R.layout.time_line_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initSetFragment();

        event();
    }

    private void initSetFragment() {

        FragmentManager fragmentManager = getChildFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.time_line_fragment, new ExpenditureFragment());
        fragmentTransaction.commit();
    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {

        clickExpenditureButton();
        clickIncomeButton();
        clickAnotherButton();
    }

    @SuppressLint("SetTextI18n")
    private void clickExpenditureButton() {

        binding.expenditureButton.setOnClickListener(v -> {

            binding.setMoney.setText("370,000");
            binding.setMoney.setTextColor(getResources().getColor(R.color.black));
            binding.setMoneyTitle.setText("원 지출");
            binding.setMoneyTitle.setTextColor(getResources().getColor(R.color.black));
            binding.etcAlertTitle.setVisibility(View.GONE);

            binding.expenditureButton.setBackgroundResource(R.drawable.time_line_button_radius);
            binding.incomeButton.setBackground(null);
            binding.anotherButton.setBackground(null);

            FragmentManager fragmentManager = getChildFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.time_line_fragment, new ExpenditureFragment());
            fragmentTransaction.commit();
        });
    }

    @SuppressLint("SetTextI18n")
    private void clickIncomeButton() {

        binding.incomeButton.setOnClickListener(v -> {

            binding.setMoney.setText("350,000");
            binding.setMoney.setTextColor(getResources().getColor(R.color.blue));
            binding.setMoneyTitle.setText("원 수입");
            binding.setMoneyTitle.setTextColor(getResources().getColor(R.color.black));
            binding.etcAlertTitle.setVisibility(View.GONE);

            binding.expenditureButton.setBackground(null);
            binding.incomeButton.setBackgroundResource(R.drawable.time_line_button_radius);
            binding.anotherButton.setBackground(null);

            FragmentManager fragmentManager = getChildFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.time_line_fragment, new IncomeFragment());
            fragmentTransaction.commit();
        });
    }

    @SuppressLint("SetTextI18n")
    private void clickAnotherButton() {

        binding.anotherButton.setOnClickListener(v -> {

            binding.setMoney.setText("70,020");
            binding.setMoney.setTextColor(getResources().getColor(R.color.gray));
            binding.setMoneyTitle.setText("원");
            binding.setMoneyTitle.setTextColor(getResources().getColor(R.color.gray));
            binding.etcAlertTitle.setVisibility(View.VISIBLE);

            binding.expenditureButton.setBackground(null);
            binding.incomeButton.setBackground(null);
            binding.anotherButton.setBackgroundResource(R.drawable.time_line_button_radius);

            FragmentManager fragmentManager = getChildFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.time_line_fragment, new AnotherFragment());
            fragmentTransaction.commit();
        });
    }
}
