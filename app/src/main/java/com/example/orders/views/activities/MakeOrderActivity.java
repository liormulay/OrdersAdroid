package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.adapters.ProductsToAddAdapter;
import com.example.orders.utils.FormatUtils;
import com.example.orders.viewmodels.MakeOrderViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MakeOrderActivity extends MenuActivity {

    public static final String ITEMS = "items";
    public static final String TOTAL = "total";

    private MakeOrderViewModel makeOrderViewModel = new MakeOrderViewModel();

    private ProductsToAddAdapter productsToAddAdapter;

    private RecyclerView productsRecycler;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private AppCompatTextView totalTextView;

    private AppCompatTextView submitTextView;

    private float total = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        findViews();
        productsToAddAdapter = new ProductsToAddAdapter(this);
        initProductsRecycler();
        getProducts();
        updateTotal();
        submitTextView.setOnClickListener(v -> onSubmitClicked());

    }

    private void onSubmitClicked() {
        compositeDisposable.add(makeOrderViewModel.onSubmit()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemResponses -> {
                    Intent intent = new Intent(MakeOrderActivity.this, CheckoutActivity.class);
                    intent.putExtra(ITEMS, itemResponses);
                    intent.putExtra(TOTAL, total);
                    startActivity(intent);
                }, throwable -> Toast.makeText(MakeOrderActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    private void findViews() {
        productsRecycler = findViewById(R.id.products_recycler);
        totalTextView = findViewById(R.id.totalTextView);
        submitTextView = findViewById(R.id.submit);
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