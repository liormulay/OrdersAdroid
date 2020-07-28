package com.example.orders.views.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.adapters.SalesProductsAdapter;
import com.example.orders.viewmodels.SalesDistributionViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class ProductsSalesDistributionActivity extends MenuActivity {

    private SalesDistributionViewModel viewModel = new SalesDistributionViewModel();

    private RecyclerView productsRecycler;

    private SalesProductsAdapter productsAdapter;

    private Disposable disposable = Disposables.disposed();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_sales_distribution);
        productsRecycler = findViewById(R.id.products_recycler);
        productsAdapter = new SalesProductsAdapter(this);
        initRecycler();
        disposable = viewModel.getProductsOrderBySale(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productSalesModels -> productsAdapter.setProducts(productSalesModels));
    }

    private void initRecycler() {
        productsRecycler.setHasFixedSize(true);
        productsRecycler.setLayoutManager(new LinearLayoutManager(this));
        productsRecycler.setAdapter(productsAdapter);
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}