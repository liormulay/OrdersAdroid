package com.example.orders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.orders.R;
import com.example.orders.views.viewholders.BaseProductViewHolder;
import com.example.orders.views.viewholders.SalesProductViewHolder;

import io.reactivex.subjects.BehaviorSubject;

public class SalesProductsAdapter extends BaseProductsAdapter {

    private Context context;

    private BehaviorSubject<Integer> productIdClicked;

    public SalesProductsAdapter(Context context, BehaviorSubject<Integer> productIdClicked) {
        this.context = context;
        this.productIdClicked = productIdClicked;
    }

    @NonNull
    @Override
    public BaseProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalesProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_sales, parent,false),productIdClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseProductViewHolder holder, int position) {
        holder.bindData(products.get(position));
    }

}
