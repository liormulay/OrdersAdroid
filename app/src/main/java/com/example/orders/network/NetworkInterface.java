package com.example.orders.network;

import com.example.orders.model.JwtTokenResponse;
import com.example.orders.model.Order;
import com.example.orders.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetworkInterface {
    @POST("/authenticate")
    Single<JwtTokenResponse> authenticate(@Body User user);

    @GET("/orders")
    Single<List<Order>> getOrders(@Header("Authorization") String token);
}
