package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.Order;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class OrdersViewModel extends ViewModel {

    public Single<List<Order>> getUserOrders(Context context) {
        return NetworkClient.getNetworkInterface()
                .getOrders(SharedPreferencesUtils.retrieveToken(context))
                .subscribeOn(Schedulers.io());
    }
}
