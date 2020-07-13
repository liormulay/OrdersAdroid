package com.example.orders.views.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.Order;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.reactivex.subjects.BehaviorSubject;


public class OrderViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView dateTextView;
    private View root;
    private Order order;

    public OrderViewHolder(@NonNull View itemView, BehaviorSubject<Order> clickedSubject) {
        super(itemView);
        dateTextView = itemView.findViewById(R.id.order_date_text_view);
        root = itemView.findViewById(R.id.order_root);
        root.setOnClickListener(v -> clickedSubject.onNext(order));
    }

    public void bindData(Order order) {
        this.order = order;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("he"));
        dateTextView.setText(dateFormat.format(order.getOrderDate()));
    }
}
