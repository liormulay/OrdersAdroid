package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.ItemRequest;
import com.example.orders.model.ItemResponse;
import com.example.orders.model.ItemsRequestModel;
import com.example.orders.model.Order;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PurchaseViewModel extends ViewModel {

    private List<ItemResponse> items;

    public PurchaseViewModel(List<ItemResponse> items) {
        this.items = items;
    }

    public Single<Order> buyRequest(Context context) {
        List<ItemRequest> itemsRequest = new ArrayList<>(items);
        ItemsRequestModel itemsRequestModel = new ItemsRequestModel(itemsRequest);
        return NetworkClient.getNetworkInterface()
                .requestBuy(SharedPreferencesUtils.retrieveToken(context),itemsRequestModel)
                .subscribeOn(Schedulers.io());
    }
}
