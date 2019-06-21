package com.migs.wowtokenprice.api.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AuthAPI {
    String BASE_URL = "https://us.battle.net/oauth/";
    String authorization = "Authorization: Basic XXXXXX";
    String contentType = "Content-Type: application/x-www-form-urlencoded";


    @GET("token")
    Call<AuthResponse> getAuth(@Header("Authorization") String authHeader);

    class Factory {
        private static AuthAPI service;

        public static AuthAPI getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                service = retrofit.create(AuthAPI.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
