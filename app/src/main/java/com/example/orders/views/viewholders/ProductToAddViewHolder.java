package com.example.orders.views.viewholders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.ItemResponse;
import com.example.orders.utils.Utils;
import com.google.common.base.Strings;
import com.squareup.picasso.Picasso;

import io.reactivex.subjects.BehaviorSubject;

public class ProductToAddViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView priceTextView;

    private AppCompatImageView productImageView;

    private AppCompatTextView productNameTextView;

    private AppCompatButton plusButton;

    private AppCompatButton minusButton;

    private AppCompatEditText quantityEditText;

    private ItemResponse itemResponse;

    private String prevText;

    private int prevQuantity;

    private BehaviorSubject<Float> addToTotal;

    public ProductToAddViewHolder(@NonNull View itemView, BehaviorSubject<Float> addToTotal) {
        super(itemView);
        priceTextView = itemView.findViewById(R.id.product_price_textView);
        productImageView = itemView.findViewById(R.id.productImageView);
        productNameTextView = itemView.findViewById(R.id.product_name_textView);
        plusButton = itemView.findViewById(R.id.plus_button);
        minusButton = itemView.findViewById(R.id.minus_button);
        quantityEditText = itemView.findViewById(R.id.quantity_editText);
        this.addToTotal = addToTotal;
    }

    public void bindData(@org.jetbrains.annotations.NotNull ItemResponse itemResponse) {
        this.itemResponse = itemResponse;
        priceTextView.setText(Utils.getRoundPrice(itemResponse.getPrice()));
        String imageUrl = itemResponse.getImageUrl();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(productImageView);
        }
        productNameTextView.setText(itemResponse.getProductName());
        setListeners();
    }

    private void setListeners() {
        plusButton.setOnClickListener(v -> {
            Editable text = quantityEditText.getText();
            int quantity = (text == null || Strings.isNullOrEmpty(text.toString())) ? 0 : Integer.parseInt(text.toString());
            ++quantity;
            quantityEditText.setText(String.valueOf(quantity));
        });

        minusButton.setOnClickListener(v -> {
            Editable text = quantityEditText.getText();
            int quantity = text == null ? 0 : Integer.parseInt(text.toString());
            --quantity;
            quantityEditText.setText(String.valueOf(quantity));
        });

        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text = s.toString();
                if (Strings.isNullOrEmpty(text)) {
                    return;
                }
                prevQuantity = Integer.parseInt(text);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (Strings.isNullOrEmpty(text)) {
                    return;
                }
                int quantity = Integer.parseInt(text);
                itemResponse.setQuantity(quantity);
                boolean enableMinus = quantity > 0;
                minusButton.setEnabled(enableMinus);
                addToTotal.onNext((quantity - prevQuantity) * itemResponse.getPrice());
            }
        });

        quantityEditText.setOnFocusChangeListener((v, hasFocus) -> {
            String text = quantityEditText.getText().toString();
            if (hasFocus) {
                this.prevText = text;
                quantityEditText.setText("");
            } else if (Strings.isNullOrEmpty(text)) {
                quantityEditText.setText(prevText);
            }
        });
    }


}
