package com.example.orders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.orders.R;
import com.example.orders.views.viewholders.BaseProductViewHolder;
import com.example.orders.views.viewholders.ProductToAddViewHolder;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class ProductsToAddAdapter extends BaseProductsAdapter{

    private Context context;


    private BehaviorSubject<Float> addToTotal = BehaviorSubject.create();

    public ProductsToAddAdapter(Context context) {
        super();
        this.context = context;
    }


    public Observable<Float> getAddToTotal() {
        return addToTotal.hide();
    }

    @NonNull
    @Override
    public ProductToAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductToAddViewHolder(LayoutInflater.from(context).inflate(R.layout.product_to_add, parent, false), addToTotal);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseProductViewHolder holder, int position) {
        holder.bindData(products.get(position));
    }

}
