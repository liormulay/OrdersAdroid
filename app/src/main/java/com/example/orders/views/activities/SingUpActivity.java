package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.ContentLoadingProgressBar;

import com.example.orders.R;
import com.example.orders.model.User;
import com.example.orders.viewmodels.SignUpViewModel;
import com.google.common.base.Strings;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class SingUpActivity extends AppCompatActivity {

    private AppCompatEditText usernameEditText;

    private AppCompatEditText passwordEditText;

    private AppCompatEditText confirmPasswordEditText;

    private AppCompatButton submitButton;

    private SignUpViewModel signUpViewModel = new SignUpViewModel();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private boolean isUsernameExist = false;

    private boolean passwordMatches = false;

    private ContentLoadingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        findViews();
        validUsername();
        validPasswords();
        submitButton.setOnClickListener(v -> onSubmit());
    }

    private void onSubmit() {
        Editable usernameEditable = usernameEditText.getText();
        Editable passwordEditable = passwordEditText.getText();
        String username = usernameEditable == null ? null : usernameEditable.toString();
        String password = passwordEditable == null ? null : passwordEditable.toString();
        if (!requiredEmpty(username, password) && !isUsernameExist && passwordMatches) {
            progressBar.setVisibility(View.VISIBLE);
            compositeDisposable.add(signUpViewModel.registerAndLogin(new User(username, password), this)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                SingUpActivity.this.startActivity(new Intent(SingUpActivity.this,
                                        HomePageActivity.getHomePageClass(this)));
                                finishAffinity();
                            },
                            throwable -> {
                                Toast.makeText(SingUpActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }));
        }
    }

    private boolean requiredEmpty(String username, String password) {
        Editable confirmPasswordEditable = confirmPasswordEditText.getText();
        String confirmPassword = confirmPasswordEditable == null ? null : confirmPasswordEditable.toString();
        boolean isRequiredEmpty = false;
        if (Strings.isNullOrEmpty(username)) {
            isRequiredEmpty = true;
            usernameEditText.setError(getString(R.string.required_fild_error_message));
        }
        if (Strings.isNullOrEmpty(password)) {
            isRequiredEmpty = true;
            passwordEditText.setError(getString(R.string.required_fild_error_message));
        }
        if (Strings.isNullOrEmpty(confirmPassword)) {
            isRequiredEmpty = true;
            confirmPasswordEditText.setError(getString(R.string.required_fild_error_message));
        }
        return isRequiredEmpty;
    }

    private void validPasswords() {
        compositeDisposable.add(Observable.combineLatest(RxTextView.textChanges(passwordEditText)
                        .map(CharSequence::toString),
                RxTextView.textChanges(confirmPasswordEditText)
                        .map(CharSequence::toString), Pair::create)
                .subscribe(passwordsPair -> {
                    passwordMatches = passwordsPair.first.equals(passwordsPair.second);
                    if (!passwordMatches) {
                        confirmPasswordEditText.setError(getString(R.string.passwords_not_matches_error_message));
                    }
                }));
    }

    private void validUsername() {
        compositeDisposable.add(RxTextView.textChanges(usernameEditText)
                .map(CharSequence::toString)
                .filter(username -> !username.isEmpty())
                .flatMapSingle(username -> signUpViewModel.isUsernameExist(username))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isUsernameExist -> {
                    SingUpActivity.this.isUsernameExist = isUsernameExist;
                    if (isUsernameExist) {
                        usernameEditText.setError(getString(R.string.username_exist_error_message));
                    }
                }, throwable -> Toast.makeText(SingUpActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    private void findViews() {
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.password_confirm_edit_text);
        submitButton = findViewById(R.id.submit_button);
        progressBar = findViewById(R.id.progress_circular);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}