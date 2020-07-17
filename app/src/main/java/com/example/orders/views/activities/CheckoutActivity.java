package com.example.orders.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.orders.R;
import com.example.orders.adapters.ItemsAdapter;
import com.example.orders.model.ItemResponse;
import com.example.orders.utils.FormatUtils;

import java.util.List;

import static com.example.orders.views.activities.MakeOrderActivity.ITEMS;
import static com.example.orders.views.activities.MakeOrderActivity.TOTAL;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView recyclerViewItems;

    private AppCompatTextView totalTextView;

    private AppCompatTextView buyTextView;

    private ItemsAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        findViews();
        itemsAdapter = new ItemsAdapter(this);
        initRecyclerItems();
        buyTextView.setText(R.string.buy);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            List<ItemResponse> items = (List<ItemResponse>) extras.getSerializable(ITEMS);
            itemsAdapter.setItems(items);
            totalTextView.setText(String.format("total %s", FormatUtils.getRoundPrice(extras.getFloat(TOTAL))));
        }
    }

    private void findViews() {
        recyclerViewItems = findViewById(R.id.products_recycler);
        totalTextView = findViewById(R.id.totalTextView);
        buyTextView = findViewById(R.id.submit);
    }

    private void initRecyclerItems() {
        recyclerViewItems.setHasFixedSize(true);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItems.setAdapter(itemsAdapter);
    }
}