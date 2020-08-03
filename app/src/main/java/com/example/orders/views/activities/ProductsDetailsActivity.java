package com.example.orders.views.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.adapters.SalesProductsAdapter;
import com.example.orders.model.ItemRequest;
import com.example.orders.viewmodels.ProductSalesViewModel;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public class ProductsDetailsActivity extends MenuActivity {

    private ProductSalesViewModel viewModel = new ProductSalesViewModel();

    private RecyclerView productsRecycler;

    private SalesProductsAdapter productsAdapter;

    private BehaviorSubject<Integer> productIdClicked = BehaviorSubject.create();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_sales_distribution);
        productsRecycler = findViewById(R.id.products_recycler);
        productsAdapter = new SalesProductsAdapter(this, productIdClicked);
        initRecycler();

        getProductsFromNetwork().subscribe();

        compositeDisposable.add(productIdClicked
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createDialog));
    }

    private Completable getProductsFromNetwork() {
        return viewModel.getProductsOrderBySale(this)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(productSalesModels -> productsAdapter.setProducts(productSalesModels))
                .ignoreElement();
    }

    private void createDialog(Integer productId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_quantity_layout, null);
        final AppCompatEditText quantityEditText = view.findViewById(R.id.quantityEditText);
        final AppCompatButton submitButton = view.findViewById(R.id.submit_button);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        submitButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(quantityEditText.getText())) {
                return;
            }
            final ContentLoadingProgressBar loadingProgressBar = view.findViewById(R.id.progress_circular);
            loadingProgressBar.setVisibility(View.VISIBLE);
            int quantity = Integer.parseInt(quantityEditText.getText().toString());
            compositeDisposable.add(viewModel.addQuantityToStock(this, new ItemRequest(productId, quantity))
                    .andThen(getProductsFromNetwork())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        loadingProgressBar.setVisibility(View.GONE);
                        dialog.dismiss();
                    }, throwable -> {
                        loadingProgressBar.setVisibility(View.GONE);
                        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }));
        });
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