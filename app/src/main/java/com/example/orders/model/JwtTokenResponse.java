package com.example.orders.model;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String token;

    private final String role;

    public JwtTokenResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}