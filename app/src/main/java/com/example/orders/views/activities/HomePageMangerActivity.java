package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;

import com.example.orders.R;

public class HomePageMangerActivity extends HomePageActivity {

    private AppCompatButton addNewProductButton;

    private AppCompatButton salesDistributionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    int getLayoutResourceId() {
        return R.layout.activity_home_page_manger;
    }

    @Override
    void findViews() {
        super.findViews();
        addNewProductButton = findViewById(R.id.add_new_product_button);
        salesDistributionButton = findViewById(R.id.products_sales_distribution_button);
    }

    @Override
    void initActions() {
        super.initActions();
        addNewProductButton.setOnClickListener(v -> startActivity(new Intent(HomePageMangerActivity.this, AddNewProductActivity.class)));
        salesDistributionButton.setOnClickListener(v -> startActivity(new Intent(HomePageMangerActivity.this, ProductsDetailsActivity.class)));
    }
}