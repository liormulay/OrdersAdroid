package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.orders.R;
import com.example.orders.model.Order;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.example.orders.views.activities.OrdersActivity.ORDER;

public class ApprovalOrderActivity extends MenuActivity {

    private AppCompatTextView messageTextView;

    private AppCompatButton homePageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_order);
        messageTextView = findViewById(R.id.message_textView);
        homePageButton = findViewById(R.id.homepage_button);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Order order = (Order) extras.getSerializable(ORDER);
            if (order != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("he"));
                messageTextView.setText(String.format("Your order will arrive at %s", dateFormat.format(order.getShipDate())));
            }
        }
        homePageButton.setOnClickListener(v -> {
            startActivity(new Intent(ApprovalOrderActivity.this, HomePageActivity.getHomePageClass(this)));
            finishAffinity();
        });
    }
}