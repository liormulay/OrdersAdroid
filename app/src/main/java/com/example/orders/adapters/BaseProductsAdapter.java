package com.example.orders.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.model.ProductBaseModel;
import com.example.orders.views.viewholders.BaseProductViewHolder;

import java.util.List;

public abstract class BaseProductsAdapter extends RecyclerView.Adapter<BaseProductViewHolder> {

    List<? extends ProductBaseModel> products;

    public void setProducts(List<? extends ProductBaseModel> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public List<? extends ProductBaseModel> getProducts() {
        return products;
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }
}
