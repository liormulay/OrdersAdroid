package com.example.orders.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;

import com.example.orders.R;
import com.example.orders.utils.SharedPreferencesUtils;

public class HomePageActivity extends MenuActivity {

    private AppCompatTextView welcomeTextView;

    private AppCompatButton makeOrderButton;

    private AppCompatButton seeOrdersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViews();
        String username = SharedPreferencesUtils.retrieveUsername(this);
        welcomeTextView.setText(String.format("%s %s", getString(R.string.welcome), username));
        initActions();
    }

    private void initActions() {
        seeOrdersButton.setOnClickListener(v -> startActivity(new Intent(HomePageActivity.this, OrdersActivity.class)));
        makeOrderButton.setOnClickListener(v -> startActivity(new Intent(HomePageActivity.this, MakeOrderActivity.class)));
    }

    private void findViews() {
        welcomeTextView = findViewById(R.id.welcome_textView);
        makeOrderButton = findViewById(R.id.make_order_button);
        seeOrdersButton = findViewById(R.id.see_orders_button);
    }
}