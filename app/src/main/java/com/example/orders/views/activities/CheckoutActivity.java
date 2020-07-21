package com.example.orders.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.adapters.ItemsAdapter;
import com.example.orders.model.ItemResponse;
import com.example.orders.utils.FormatUtils;
import com.example.orders.viewmodels.PurchaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

import static com.example.orders.views.activities.MakeOrderActivity.ITEMS;
import static com.example.orders.views.activities.MakeOrderActivity.TOTAL;

public class CheckoutActivity extends MenuActivity {

    private RecyclerView recyclerViewItems;

    private AppCompatTextView totalTextView;

    private AppCompatTextView buyTextView;

    private ItemsAdapter itemsAdapter;

    private PurchaseViewModel purchaseViewModel;

    private static String ORDER = "ORDER";

    private Disposable disposable = Disposables.disposed();

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
            purchaseViewModel = new PurchaseViewModel(items);
            buyTextView.setOnClickListener(v ->
                    disposable = purchaseViewModel.buyRequest(this)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(order -> {
                                Intent intent = new Intent(CheckoutActivity.this, ApprovalOrderActivity.class);
                                intent.putExtra(ORDER, order);
                                startActivity(intent);
                                finishAffinity();
                            }, throwable -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show()));
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

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}