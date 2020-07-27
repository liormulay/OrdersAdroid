package com.example.orders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.orders.R;
import com.example.orders.views.viewholders.BaseProductViewHolder;
import com.example.orders.views.viewholders.ItemViewHolder;

public class ItemsAdapter extends BaseProductsAdapter {

    private Context context;

    public ItemsAdapter(Context context) {
        super();
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseProductViewHolder holder, int position) {
        holder.bindData(products.get(position));
    }

}
