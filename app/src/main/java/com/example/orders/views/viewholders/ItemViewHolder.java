package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.orders.R;
import com.example.orders.model.ItemResponse;
import com.example.orders.model.ProductBaseModel;
import com.example.orders.utils.Utils;

public class ItemViewHolder extends BaseProductViewHolder {

    private AppCompatTextView quantityTextView;

    private AppCompatTextView priceTextView;

    private AppCompatTextView totalTextView;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        quantityTextView = itemView.findViewById(R.id.quantity_textView);
        priceTextView = itemView.findViewById(R.id.price_textView);
        totalTextView = itemView.findViewById(R.id.total_textView);
    }

    @Override
    public void bindData(ProductBaseModel model) {
        super.bindData(model);
        ItemResponse itemResponse = (ItemResponse) model;
        quantityTextView.setText(String.valueOf(itemResponse.getQuantity()));
        priceTextView.setText(String.format("%s $", itemResponse.getPrice()));
        totalTextView.setText(String.format("%s $", Utils.getRoundPrice(itemResponse.getQuantity() * itemResponse.getPrice())));
    }

}
