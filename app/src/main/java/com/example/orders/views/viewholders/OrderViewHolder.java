package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.Order;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView dateTextView;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        dateTextView = itemView.findViewById(R.id.order_date_text_view);
    }

    public void bindData(Order order) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("he"));
        dateTextView.setText(dateFormat.format(order.getOrderDate()));
    }
}
