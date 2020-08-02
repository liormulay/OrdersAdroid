package com.example.orders.views.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.adapters.SalesProductsAdapter;
import com.example.orders.viewmodels.SalesDistributionViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class ProductsDetailsActivity extends MenuActivity {

    private SalesDistributionViewModel viewModel = new SalesDistributionViewModel();

    private RecyclerView productsRecycler;

    private SalesProductsAdapter productsAdapter;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_sales_distribution);
        productsRecycler = findViewById(R.id.products_recycler);
        productsAdapter = new SalesProductsAdapter(this);
        initRecycler();
        compositeDisposable.add(viewModel.getProductsOrderBySale(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productSalesModels -> productsAdapter.setProducts(productSalesModels)));
    }

    private void initRecycler() {
        productsRecycler.setHasFixedSize(true);
        productsRecycler.setLayoutManager(new LinearLayoutManager(this));
        productsRecycler.setAdapter(productsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_with_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_quantity_ascendant:
                compositeDisposable.add(viewModel.sortByQuantityAscendant(productsAdapter.getProducts())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> productsAdapter.setProducts(products)));
                return true;
            case R.id.sort_by_quantity_descendant:
                compositeDisposable.add(viewModel.sortByQuantityDescendant(productsAdapter.getProducts())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> productsAdapter.setProducts(products)));
                return true;
            case R.id.sort_by_sales_ascendant:
                compositeDisposable.add(viewModel.sortBySalesAscendant(productsAdapter.getProducts())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> productsAdapter.setProducts(products)));
                return true;
            case R.id.sort_by_sales_descendant:
                compositeDisposable.add(viewModel.sortBySalesDescendant(productsAdapter.getProducts())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(products -> productsAdapter.setProducts(products)));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}