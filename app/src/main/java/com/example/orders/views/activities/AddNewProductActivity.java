package com.example.orders.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.orders.R;

public class AddNewProductActivity extends MenuActivity {

    private AppCompatImageView productImage;

    private AppCompatEditText productNameEditText;

    private AppCompatEditText productQuantityEditText;

    private AppCompatEditText productPriceEditText;

    private AppCompatTextView submitTextView;

    private Uri productImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        findViews();
        initActions();
    }

    private void initActions() {
        productImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            productImageUri = data.getData();
            productImage.setImageURI(productImageUri);
        }
    }

    private void findViews() {
        productImage = findViewById(R.id.product_image);
        productNameEditText = findViewById(R.id.product_name_editText);
        productPriceEditText = findViewById(R.id.quantity_editText);
        productQuantityEditText = findViewById(R.id.price_editText);
        submitTextView = findViewById(R.id.submit_text_view);
    }
}