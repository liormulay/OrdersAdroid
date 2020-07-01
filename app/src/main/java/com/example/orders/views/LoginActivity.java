package com.example.orders.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.orders.R;
import com.example.orders.model.User;
import com.example.orders.viewmodels.LoginViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText usernameEditText;

    private AppCompatEditText passwordEditText;

    private AppCompatButton loginButton;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new LoginViewModel();
        findViews();
        initActions();

    }

    private void initActions() {
        loginButton.setOnClickListener(v -> {
            String username = null, password = null;
            if (!TextUtils.isEmpty(usernameEditText.getText())) {
                username = usernameEditText.getText().toString();
            } else {
                usernameEditText.setError("username is required");
            }
            if (!TextUtils.isEmpty(passwordEditText.getText())) {
                password = passwordEditText.getText().toString();
            } else {
                passwordEditText.setError("password is required");
            }
            if (username != null && password != null) {
                User user = new User(username, password);
                loginViewModel.onLoginClicked(user, this)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> startActivity(new Intent(LoginActivity.this, HomePageActivity.class)));
            }
        });
    }

    private void findViews() {
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);
    }
}