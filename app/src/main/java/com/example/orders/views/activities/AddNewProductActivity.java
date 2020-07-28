package com.example.orders.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ContentLoadingProgressBar;

import com.example.orders.R;
import com.example.orders.utils.Utils;
import com.example.orders.viewmodels.AddNewProductViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class AddNewProductActivity extends MenuActivity {

    private AppCompatImageView productImage;

    private AppCompatEditText productNameEditText;

    private AppCompatEditText productQuantityEditText;

    private AppCompatEditText productPriceEditText;

    private AppCompatTextView submitTextView;

    private ContentLoadingProgressBar progressBar;

    private Uri productImageUri;

    private AddNewProductViewModel addNewProductViewModel = new AddNewProductViewModel();

    private Disposable disposable = Disposables.disposed();

    public static final String PRODUCT_NAME = "product name";

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

        submitTextView.setOnClickListener(v -> {
            if (!validRequired()) {
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            String productName = productNameEditText.getText().toString();
            int productQuantity = Integer.parseInt(productQuantityEditText.getText().toString());
            float productPrice = Float.parseFloat(productPriceEditText.getText().toString());
            disposable = addNewProductViewModel.onSubmit(this, productName, productQuantity, productPrice, productImageUri)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(AddNewProductActivity.this, ApprovalNewProductActivity.class);
                        intent.putExtra(PRODUCT_NAME, productName);
                        startActivity(intent);
                        finishAffinity();
                    }, throwable -> {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                        throwable.printStackTrace();
                    });
        });
    }

    private boolean validRequired() {
        boolean isProductNameFull = Utils.validRequired(productNameEditText);
        boolean isQuantityFull = Utils.validRequired(productQuantityEditText);
        boolean isPriceFull = Utils.validRequired(productPriceEditText);
        return isProductNameFull && isQuantityFull && isPriceFull;
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
        productQuantityEditText = findViewById(R.id.quantity_editText);
        productPriceEditText = findViewById(R.id.price_editText);
        submitTextView = findViewById(R.id.submit_text_view);
        progressBar = findViewById(R.id.progress_circular);
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}