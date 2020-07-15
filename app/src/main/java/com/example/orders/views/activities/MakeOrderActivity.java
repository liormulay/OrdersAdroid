package com.example.orders.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.AndroidException;

import com.example.orders.R;
import com.example.orders.adapters.ProductsToAddAdapter;
import com.example.orders.utils.FormatUtils;
import com.example.orders.viewmodels.MakeOrderViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MakeOrderActivity extends AppCompatActivity {

    private MakeOrderViewModel makeOrderViewModel = new MakeOrderViewModel();

    private ProductsToAddAdapter productsToAddAdapter;

    private RecyclerView productsRecycler;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private AppCompatTextView totalTextView;

    private float total = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        productsRecycler = findViewById(R.id.products_recycler);
        totalTextView = findViewById(R.id.totalTextView);
        productsToAddAdapter = new ProductsToAddAdapter(this);
        initProductsRecycler();
        getProducts();
        updateTotal();
    }

    private void updateTotal() {
        totalTextView.setText(String.format("total %s", total));
        compositeDisposable.add(productsToAddAdapter.getAddToTotal()
                .subscribe(addToTotal -> {
                    MakeOrderActivity.this.total += addToTotal;
                    totalTextView.setText(String.format("total %s", FormatUtils.getRoundPrice(total)));
                }));
    }

    private void getProducts() {
        compositeDisposable.add(makeOrderViewModel.getProducts(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemResponses -> productsToAddAdapter.setItems(itemResponses)));
    }

    private void initProductsRecycler() {
        productsRecycler.setHasFixedSize(true);
        productsRecycler.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        productsRecycler.setAdapter(productsToAddAdapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}