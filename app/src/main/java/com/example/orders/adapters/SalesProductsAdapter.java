package com.example.orders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.orders.R;
import com.example.orders.views.viewholders.BaseProductViewHolder;
import com.example.orders.views.viewholders.SalesProductViewHolder;

public class SalesProductsAdapter extends BaseProductsAdapter {

    private Context context;

    public SalesProductsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalesProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_sales, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseProductViewHolder holder, int position) {
        holder.bindData(products.get(position));
    }


}
