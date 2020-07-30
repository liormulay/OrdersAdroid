package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.User;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;
import com.google.common.base.Strings;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;


public class LoginViewModel extends ViewModel {


    public Completable isLoggedIn(Context context) {
        String token = SharedPreferencesUtils.retrieveToken(context);
        if (Strings.isNullOrEmpty(token)) {
            return Completable.error(new Exception("empty token"));
        }
        return NetworkClient.getNetworkInterface()
                .checkToken(token)
                .subscribeOn(Schedulers.io());
    }

    public Completable doLogin(User user, Context context) {
        return NetworkClient.getNetworkInterface()
                .authenticate(user)
                .subscribeOn(Schedulers.io())
                .flatMapCompletable(jwtTokenResponse -> {
                    SharedPreferencesUtils.saveToken(context, jwtTokenResponse.getToken());
                    SharedPreferencesUtils.saveUsername(context, jwtTokenResponse.getUser().getUserName());
                    SharedPreferencesUtils.saveRole(context, jwtTokenResponse.getUser().getRole());
                    return Completable.complete();
                });
    }


}
