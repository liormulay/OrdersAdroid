package com.example.orders.network;

import com.example.orders.model.JwtTokenResponse;
import com.example.orders.model.User;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkInterface {
    @POST("/authenticate")
    Single<JwtTokenResponse> authenticate(@Body User user);
}
