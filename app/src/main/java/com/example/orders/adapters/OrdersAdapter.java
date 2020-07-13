package com.example.orders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orders.R;
import com.example.orders.model.Order;
import com.example.orders.views.viewholders.OrderViewHolder;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;


public class OrdersAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private Context context;

    private List<Order> orders;

    private BehaviorSubject<Order> clickedSubject;

    public OrdersAdapter(Context context, BehaviorSubject<Order> clickedSubject) {
        this.context = context;
        this.clickedSubject = clickedSubject;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.order_view_holder, parent, false), clickedSubject);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bindData(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }
}
