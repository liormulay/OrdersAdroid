package com.example.orders.views.activities;

import android.os.Bundle;

import com.example.orders.R;

public class HomePageMangerActivity extends HomePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_manger);
        findViews();
        initWelcomeMessage();
        initActions();
    }
}