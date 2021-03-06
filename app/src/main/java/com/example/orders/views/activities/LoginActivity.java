package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ContentLoadingProgressBar;

import com.example.orders.R;
import com.example.orders.model.User;
import com.example.orders.utils.Utils;
import com.example.orders.viewmodels.LoginViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText usernameEditText;

    private AppCompatEditText passwordEditText;

    private AppCompatButton loginButton;

    private AppCompatTextView signUpTextView;

    private ContentLoadingProgressBar progressBar;

    private LoginViewModel loginViewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new LoginViewModel();
        compositeDisposable.add(loginViewModel.isLoggedIn(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::goToHomePage, throwable -> {}));
        findViews();
        initActions();

    }

    private void initActions() {
        loginButton.setOnClickListener(v -> {
            String username = null, password = null;
            if (Utils.validRequired(usernameEditText)) {
                username = usernameEditText.getText().toString();
            }
            if (Utils.validRequired(passwordEditText)) {
                password = passwordEditText.getText().toString();
            }
            if (username != null && password != null) {
                progressBar.setVisibility(View.VISIBLE);
                User user = new User(username, password);
                compositeDisposable.add(loginViewModel.doLogin(user, this)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::goToHomePage,
                                throwable -> {
                                    Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }));
            }
        });

        signUpTextView.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SingUpActivity.class)));
    }

    private void goToHomePage() {
        Intent intent = new Intent(LoginActivity.this, HomePageActivity.getHomePageClass(this));
        LoginActivity.this.startActivity(intent);
        finishAffinity();
    }

    private void findViews() {
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_circular);
        signUpTextView = findViewById(R.id.sign_up_text_view);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}