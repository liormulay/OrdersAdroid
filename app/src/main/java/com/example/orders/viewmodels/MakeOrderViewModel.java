package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.ItemResponse;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MakeOrderViewModel extends ViewModel {

    public Single<List<ItemResponse>> getProducts(Context context) {
        return NetworkClient.getNetworkInterface()
                .getProducts(SharedPreferencesUtils.retrieveToken(context))
                .subscribeOn(Schedulers.io());
    }
}
