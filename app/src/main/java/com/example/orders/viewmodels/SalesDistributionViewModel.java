package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.ProductBaseModel;
import com.example.orders.model.ProductSalesModel;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class SalesDistributionViewModel extends ViewModel {

    public Single<List<ProductSalesModel>> getProductsOrderBySale(Context context) {
        return NetworkClient.getNetworkInterface()
                .getProductsOrderBySale(SharedPreferencesUtils.retrieveToken(context))
                .subscribeOn(Schedulers.io());
    }

    public Single<List<ProductSalesModel>> sortByQuantityAscendant(List<? extends ProductBaseModel> products) {
        return Observable.fromIterable(products)
                .map(product -> (ProductSalesModel) product)
                .sorted((product1, product2) -> product1.getStockQuantity() - product2.getStockQuantity())
                .toList()
                .subscribeOn(Schedulers.io());
    }

    public Single<List<ProductSalesModel>> sortByQuantityDescendant(List<? extends ProductBaseModel> products) {
        return Observable.fromIterable(products)
                .map(product -> (ProductSalesModel) product)
                .sorted((product1, product2) -> product2.getStockQuantity() - product1.getStockQuantity())
                .toList()
                .subscribeOn(Schedulers.io());
    }

    public Single<List<ProductSalesModel>> sortBySalesAscendant(List<? extends ProductBaseModel> products) {
        return Observable.fromIterable(products)
                .map(product -> (ProductSalesModel) product)
                .sorted((product1, product2) -> product1.getSalesQuantity() - product2.getSalesQuantity())
                .toList()
                .subscribeOn(Schedulers.io());
    }

    public Single<List<ProductSalesModel>>  sortBySalesDescendant(List<? extends ProductBaseModel> products) {
        return Observable.fromIterable(products)
                .map(product -> (ProductSalesModel) product)
                .sorted((product1, product2) -> product2.getSalesQuantity() - product1.getSalesQuantity())
                .toList()
                .subscribeOn(Schedulers.io());
    }
}
