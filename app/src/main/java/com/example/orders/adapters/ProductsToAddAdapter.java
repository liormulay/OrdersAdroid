package com.example.orders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.ItemResponse;
import com.example.orders.views.viewholders.ProductToAddViewHolder;

import java.util.List;

public class ProductsToAddAdapter extends RecyclerView.Adapter<ProductToAddViewHolder> {

    private Context context;

    private List<ItemResponse> items;

    public ProductsToAddAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<ItemResponse> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductToAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductToAddViewHolder(LayoutInflater.from(context).inflate(R.layout.product_to_add, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductToAddViewHolder holder, int position) {
        holder.bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
