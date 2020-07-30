package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.orders.R;
import com.example.orders.model.ProductBaseModel;
import com.example.orders.model.ProductSalesModel;

public class SalesProductViewHolder extends BaseProductViewHolder {

    private AppCompatTextView stockQuantityTextView;

    private AppCompatTextView priceTextView;

    private AppCompatTextView salesTextView;

    public SalesProductViewHolder(@NonNull View itemView) {
        super(itemView);
        stockQuantityTextView = itemView.findViewById(R.id.stock_quantity_textView);
        priceTextView = itemView.findViewById(R.id.price_textView);
        salesTextView = itemView.findViewById(R.id.total_sales_textView);
    }

    @Override
    public void bindData(ProductBaseModel model) {
        super.bindData(model);
        ProductSalesModel productSalesModel = (ProductSalesModel) model;
        stockQuantityTextView.setText(String.format("quantity in the stock: %s", productSalesModel.getStockQuantity()));
        priceTextView.setText(String.format("price: %s $", productSalesModel.getPrice()));
        salesTextView.setText(String.format("total sales: %s", productSalesModel.getSalesQuantity()));
    }
}
