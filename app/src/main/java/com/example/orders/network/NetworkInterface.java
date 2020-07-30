package com.example.orders.network;

import com.example.orders.model.ItemResponse;
import com.example.orders.model.ItemsRequestModel;
import com.example.orders.model.JwtTokenResponse;
import com.example.orders.model.Order;
import com.example.orders.model.Product;
import com.example.orders.model.ProductSalesModel;
import com.example.orders.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkInterface {


    @GET("/is_username_exist/{username}")
    Single<Boolean> isUsernameExist(@Path("username") String username);

    @POST("/register")
    Single<User> register(@Body User user);

    @POST("/authenticate")
    Single<JwtTokenResponse> authenticate(@Body User user);

    @GET("/orders")
    Single<List<Order>> getOrders(@Header("Authorization") String token);

    @GET("/request_items")
    Single<List<ItemResponse>> getProducts(@Header("Authorization") String token);

    @POST("/order")
    Single<Order> requestBuy(@Header("Authorization") String token, @Body ItemsRequestModel itemsRequestModel);

    @POST("/product")
    Completable addNewProduct(@Header("Authorization") String token, @Body Product product);

    @GET("/productsOrderBySale")
    Single<List<ProductSalesModel>> getProductsOrderBySale(@Header("Authorization") String token);

    @GET("/check-token")
    Completable checkToken(@Header("Authorization") String token);
}
