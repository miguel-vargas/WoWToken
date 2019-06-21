package com.migs.wowtokenprice.api.model;

public class AuthResponse {
    private String access_token;
    private String token_type;
    private int expired_in;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setExpired_in(int expired_in) {
        this.expired_in = expired_in;
    }
}
