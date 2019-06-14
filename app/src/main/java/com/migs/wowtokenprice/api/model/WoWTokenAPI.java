package com.migs.wowtokenprice.api.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface WoWTokenAPI {
    String BASE_URL = "https://us.api.blizzard.com/";

    @GET("data/wow/token/index")
    Call<WoWTokenResponse> getWoWToken();

    class Factory {
        private static WoWTokenAPI service;

        public static WoWTokenAPI getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                service = retrofit.create(WoWTokenAPI.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
