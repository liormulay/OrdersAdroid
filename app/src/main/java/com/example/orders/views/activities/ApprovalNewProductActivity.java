package com.example.orders.views.activities;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import static com.example.orders.views.activities.AddNewProductActivity.PRODUCT_NAME;

public class ApprovalNewProductActivity extends ApprovalActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setMessage(@NotNull Bundle extras) {
        String productName = extras.getString(PRODUCT_NAME);
        this.messageTextView.setText(String.format("%s was added successfully", productName));
    }
}