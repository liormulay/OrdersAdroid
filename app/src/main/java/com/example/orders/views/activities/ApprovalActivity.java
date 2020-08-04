package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.orders.R;

import org.jetbrains.annotations.NotNull;

public abstract class ApprovalActivity extends MenuActivity {

    AppCompatTextView messageTextView;

    private AppCompatImageView approvalImage;

    private AppCompatButton homePageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        findViews();
        approvalImage.setImageDrawable(getDrawable(getApprovalImage()));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setMessage(extras);
        }
        homePageButton.setOnClickListener(v -> {
            startActivity(new Intent(ApprovalActivity.this, HomePageActivity.getHomePageClass(this)));
            finishAffinity();
        });
    }

    protected int getApprovalImage() {
        return R.mipmap.ic_approval_foreground;
    }

    private void findViews() {
        messageTextView = findViewById(R.id.message_textView);
        homePageButton = findViewById(R.id.homepage_button);
        approvalImage = findViewById(R.id.approval_image);
    }

    protected abstract void setMessage(@NotNull Bundle extras);
}