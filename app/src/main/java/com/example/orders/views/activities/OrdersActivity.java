package com.example.orders.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;

import com.example.orders.R;
import com.example.orders.adapters.OrdersAdapter;
import com.example.orders.viewmodels.OrdersViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class OrdersActivity extends AppCompatActivity {

    private OrdersViewModel ordersViewModel;

    private OrdersAdapter ordersAdapter;

    private RecyclerView ordersRecyclerView;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        ordersViewModel = new OrdersViewModel();
        ordersAdapter = new OrdersAdapter(this);
        ordersRecyclerView = findViewById(R.id.orders_recyclerView);
        initOrdersRecycler();
        getOrders();
    }

    protected void getOrders() {
        compositeDisposable.add(ordersViewModel.getUserOrders(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> ordersAdapter.setOrders(orders)));
    }

    protected void initOrdersRecycler() {
        ordersRecyclerView.setHasFixedSize(true);
        ordersRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        ordersRecyclerView.setAdapter(ordersAdapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}