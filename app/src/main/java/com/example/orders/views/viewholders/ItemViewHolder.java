package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.ItemResponse;
import com.example.orders.utils.Utils;
import com.squareup.picasso.Picasso;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView productImageView;

    private AppCompatTextView productNameTextView;

    private AppCompatTextView quantityTextView;

    private AppCompatTextView priceTextView;

    private AppCompatTextView totalTextView;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        productNameTextView = itemView.findViewById(R.id.product_name_textView);
        productImageView = itemView.findViewById(R.id.productImageView);
        quantityTextView = itemView.findViewById(R.id.quantity_textView);
        priceTextView = itemView.findViewById(R.id.price_textView);
        totalTextView = itemView.findViewById(R.id.total_textView);
    }

    public void bindData(ItemResponse itemResponse) {
        String imageUrl = itemResponse.getImageUrl();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(productImageView);
        }
        productNameTextView.setText(itemResponse.getProductName());
        quantityTextView.setText(String.valueOf(itemResponse.getQuantity()));
        priceTextView.setText(String.valueOf(itemResponse.getPrice()));
        totalTextView.setText(Utils.getRoundPrice(itemResponse.getQuantity() * itemResponse.getPrice()));
    }
}
