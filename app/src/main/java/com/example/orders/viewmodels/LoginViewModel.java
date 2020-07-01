package com.example.orders.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.User;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;


public class LoginViewModel extends ViewModel {


    public Completable onLoginClicked(User user, Context context) {
        return NetworkClient.getNetworkInterface()
                .authenticate(user)
                .subscribeOn(Schedulers.io())
                .flatMapCompletable(jwtTokenResponse -> {
                    SharedPreferencesUtils.saveToken(context, jwtTokenResponse.getToken());
                    SharedPreferencesUtils.saveRole(context, jwtTokenResponse.getRole());
                    return Completable.complete();
                });
    }


}
