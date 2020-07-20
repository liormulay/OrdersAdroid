package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.adapters.OrdersAdapter;
import com.example.orders.model.Order;
import com.example.orders.viewmodels.OrdersViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;


public class OrdersActivity extends MenuActivity {

    public static final String ORDER = "ORDER";

    private OrdersViewModel ordersViewModel;

    private OrdersAdapter ordersAdapter;

    private RecyclerView ordersRecyclerView;

    private ContentLoadingProgressBar progressBar;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private BehaviorSubject<Order> clickedSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        ordersViewModel = new OrdersViewModel();
        ordersAdapter = new OrdersAdapter(this, clickedSubject);
        ordersRecyclerView = findViewById(R.id.orders_recyclerView);
        progressBar = findViewById(R.id.progress_circular);
        initOrdersRecycler();
        getOrders();
        compositeDisposable.add(clickedSubject
                .subscribe(order -> {
                    Intent intent = new Intent(OrdersActivity.this, OrderActivity.class);
                    intent.putExtra(ORDER, order);
                    startActivity(intent);
                }));
    }

    protected void getOrders() {
        compositeDisposable.add(ordersViewModel.getUserOrders(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    ordersAdapter.setOrders(orders);
                    progressBar.setVisibility(View.GONE);
                }));
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