package com.example.orders.model;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String token;

    private final User user;

    public JwtTokenResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }


}