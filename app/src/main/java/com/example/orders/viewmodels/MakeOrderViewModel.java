package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.ItemResponse;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MakeOrderViewModel extends ViewModel {

    private List<ItemResponse> items;

    public Single<List<ItemResponse>> getProducts(Context context) {
        return NetworkClient.getNetworkInterface()
                .getProducts(SharedPreferencesUtils.retrieveToken(context))
                .subscribeOn(Schedulers.io())
                .doOnSuccess(itemsResponse -> items = itemsResponse);
    }

    public Observable<ArrayList<ItemResponse>> onSubmit() {
        ArrayList<ItemResponse> itemsToBuy = new ArrayList<>();
        for (ItemResponse itemResponse : items) {
            if (itemResponse.getQuantity() > 0) {
                itemsToBuy.add(itemResponse);
            }
        }
        BehaviorSubject<ArrayList<ItemResponse>> itemsToBuySubject = BehaviorSubject.create();
        if (itemsToBuy.isEmpty()) {
            itemsToBuySubject.onError(new Exception("No item selected"));
        } else {
            itemsToBuySubject.onNext(itemsToBuy);
        }
        return itemsToBuySubject.hide();
    }
}
