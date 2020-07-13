package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.ItemResponse;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        Picasso.get().load(itemResponse.getImageUrl()).into(productImageView);
        productNameTextView.setText(itemResponse.getProductName());
        quantityTextView.setText(String.valueOf(itemResponse.getQuantity()));
        priceTextView.setText(String.valueOf(itemResponse.getPrice()));
        String total = String.valueOf(itemResponse.getQuantity() * itemResponse.getPrice());
        BigDecimal bigDecimal = new BigDecimal(total);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_DOWN);
        totalTextView.setText(String.valueOf(bigDecimal.floatValue()));
    }
}
