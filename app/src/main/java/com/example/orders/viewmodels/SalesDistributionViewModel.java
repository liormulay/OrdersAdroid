package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.ProductSalesModel;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class SalesDistributionViewModel extends ViewModel {

    public Single<List<ProductSalesModel>> getProductsOrderBySale(Context context) {
        return NetworkClient.getNetworkInterface()
                .getProductsOrderBySale(SharedPreferencesUtils.retrieveToken(context))
                .subscribeOn(Schedulers.io());
    }
}
