package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.ItemResponse;
import com.example.orders.model.ProductBaseModel;
import com.squareup.picasso.Picasso;

public abstract class BaseProductViewHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView productImageView;

    private AppCompatTextView productNameTextView;

    public BaseProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productNameTextView = itemView.findViewById(R.id.product_name_textView);
        productImageView = itemView.findViewById(R.id.productImageView);
    }

    public void bindData(ProductBaseModel model) {
        String imageUrl = model.getImageUrl();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(productImageView);
        }
        productNameTextView.setText(model.getProductName());
    }
}
