package com.migs.wowtokenprice.api.model;

public class AuthResponse {
    private String access_token;
    private String token_type;
    private int expires_in;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
    }
}
