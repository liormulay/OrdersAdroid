package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.orders.R;
import com.example.orders.model.ProductBaseModel;
import com.example.orders.model.ProductSalesModel;

public class SalesProductViewHolder extends BaseProductViewHolder {

    private AppCompatTextView soldTextView;

    public SalesProductViewHolder(@NonNull View itemView) {
        super(itemView);
        soldTextView = itemView.findViewById(R.id.total_sales_textView);
    }

    @Override
    public void bindData(ProductBaseModel model) {
        super.bindData(model);
        int totalSales = ((ProductSalesModel) model).getSalesQuantity();
        soldTextView.setText(String.format("total sales: %s", totalSales));
    }
}
