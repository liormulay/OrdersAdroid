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
import com.google.common.base.Strings;
import com.squareup.picasso.Picasso;

public class ProductToAddViewHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView productImageView;

    private AppCompatTextView productNameTextView;

    private AppCompatButton plusButton;

    private AppCompatButton minusButton;

    private AppCompatEditText quantityEditText;

    private ItemResponse itemResponse;

    public ProductToAddViewHolder(@NonNull View itemView) {
        super(itemView);
        productImageView = itemView.findViewById(R.id.productImageView);
        productNameTextView = itemView.findViewById(R.id.product_name_textView);
        plusButton = itemView.findViewById(R.id.plus_button);
        minusButton = itemView.findViewById(R.id.minus_button);
        quantityEditText = itemView.findViewById(R.id.quantity_editText);
    }

    public void bindData(@org.jetbrains.annotations.NotNull ItemResponse itemResponse) {
        this.itemResponse = itemResponse;
        Picasso.get().load(itemResponse.getImageUrl()).into(productImageView);
        productNameTextView.setText(itemResponse.getProductName());
        setListeners();
    }

    private void setListeners() {
        plusButton.setOnClickListener(v -> {
            Editable text = quantityEditText.getText();
            int quantity = text == null ? 0 : Integer.parseInt(text.toString());
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
            }
        });

        quantityEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && quantityEditText.getText() == null || Strings.isNullOrEmpty(quantityEditText.getText().toString())) {
                quantityEditText.setText("0");
            }
        });
    }


}
