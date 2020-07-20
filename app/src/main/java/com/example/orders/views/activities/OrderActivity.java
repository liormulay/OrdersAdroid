package com.example.orders.views.activities;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.adapters.ItemsAdapter;
import com.example.orders.model.Order;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.example.orders.views.activities.OrdersActivity.ORDER;

public class OrderActivity extends MenuActivity {

    private AppCompatTextView orderDate;

    private RecyclerView recyclerViewItems;

    private AppCompatTextView total;

    private ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        findViews();
        itemsAdapter = new ItemsAdapter(this);
        initRecyclerItems();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Order order = (Order) extras.getSerializable(ORDER);
            bindItems(order);
        }
    }

    private void bindItems(Order order) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("he"));
        orderDate.setText(dateFormat.format(order.getOrderDate()));
        itemsAdapter.setItems(order.getItems());
        total.setText(String.format("total %s", order.getTotal()));
    }

    private void initRecyclerItems() {
        recyclerViewItems.setHasFixedSize(true);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItems.setAdapter(itemsAdapter);
    }

    private void findViews() {
        orderDate = findViewById(R.id.date_textView);
        recyclerViewItems = findViewById(R.id.recycler_items);
        total = findViewById(R.id.totalTextView);
    }
}