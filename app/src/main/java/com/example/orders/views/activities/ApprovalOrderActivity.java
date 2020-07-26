package com.example.orders.views.activities;

import android.os.Bundle;

import com.example.orders.R;
import com.example.orders.model.Order;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.example.orders.views.activities.OrdersActivity.ORDER;

public class ApprovalOrderActivity extends ApprovalActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getApprovalImage() {
         return R.mipmap.ic_approved_order;
    }

    @Override
    protected void setMessage(@NotNull Bundle extras) {
        Order order = (Order) extras.getSerializable(ORDER);
        if (order != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("he"));
            messageTextView.setText(String.format("Your order will arrive at %s", dateFormat.format(order.getShipDate())));
        }
    }
}