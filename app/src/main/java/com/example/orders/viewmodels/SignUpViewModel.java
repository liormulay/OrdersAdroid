package com.example.orders.viewmodels;

import android.content.Context;

import com.example.orders.model.User;
import com.example.orders.network.NetworkClient;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class SignUpViewModel extends LoginViewModel {

    public Single<Boolean> isUsernameExist(String username) {
        return NetworkClient.getNetworkInterface()
                .isUsernameExist(username)
                .subscribeOn(Schedulers.io());
    }

    public Completable registerAndLogin(User user, Context context) {
        return NetworkClient.getNetworkInterface()
                .register(user).ignoreElement()
                .andThen(doLogin(user, context))
                .subscribeOn(Schedulers.io());
    }
}
