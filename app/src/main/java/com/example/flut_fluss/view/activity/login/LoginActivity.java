package com.example.flut_fluss.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.flut_fluss.R;
import com.example.flut_fluss.base.BaseActivity;
import com.example.flut_fluss.manager.Token;
import com.example.flut_fluss.databinding.LoginActivityBinding;
import com.example.flut_fluss.manager.factory.ViewModelFactory;
import com.example.flut_fluss.manager.sqlite.CurrentUser;
import com.example.flut_fluss.network.request.LoginRequest;
import com.example.flut_fluss.view.activity.main.MainActivity;
import com.example.flut_fluss.view.activity.register.RegisterActivity;
import com.example.flut_fluss.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginActivityBinding> {

    private boolean checkBlind = false;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        initData();

        loginViewModel.getData().observe(this, login -> {

            new Token(this).setToken(login.getToken().getToken());
            new CurrentUser(this, "flut_database", null, 1).insert(login.getInfo());

            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();

            if(login.getInfo().getId() == null) {

                return;
            }
            else {

                startActivity(new Intent(this, MainActivity.class));
            }
        });

        loginViewModel.getErrorMessage().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());

        event();
    }

    private void initData() {

    }

    private void initViewModel() {

        loginViewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(LoginViewModel.class);
    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {

        clickPasswordClearButton();
        clickBlindButton();
        clickLoginButton();
        clickRegisterButton();
    }

    private void clickPasswordClearButton() {

        binding.pwClearButton.setOnClickListener(v -> {

            binding.passwordText.setText("");
        });
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

                Toast.makeText(this, "아이디를 입력해 주세요", Toast.LENGTH_SHORT).show();

                return;
            }
            if (binding.passwordText.getText().toString().isEmpty()) {

                Toast.makeText(this, "비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show();

                return;
            }
            else {

                loginViewModel.login(new LoginRequest(binding.idText.getText().toString(), binding.passwordText.getText().toString()));
            }
        });
    }

    private void clickRegisterButton() {

        binding.registerButton.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });
    }

    @Override
    protected int layoutId() {

        return R.layout.login_activity;
    }
}
