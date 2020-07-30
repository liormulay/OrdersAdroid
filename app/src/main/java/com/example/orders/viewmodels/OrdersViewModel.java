package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.Order;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class OrdersViewModel extends ViewModel {

    /**
     *
     * @param context
     * @return orders sorted descendant by their date
     */
    public Single<List<Order>> getUserOrders(Context context) {
        return NetworkClient.getNetworkInterface()
                .getOrders(SharedPreferencesUtils.retrieveToken(context))
                .flatMapObservable(Observable::fromIterable)
                .sorted((order1, order2) -> order2.getOrderDate().compareTo(order1.getOrderDate()))
                .toList()
                .subscribeOn(Schedulers.io());
    }
}
