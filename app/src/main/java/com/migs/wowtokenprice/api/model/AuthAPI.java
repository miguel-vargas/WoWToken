package com.migs.wowtokenprice.api.model;

import com.migs.wowtokenprice.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthAPI {
    String BASE_URL = "https://us.battle.net/oauth/";

    @FormUrlEncoded
    @POST("token")
    Call<AuthResponse> getAuth(@Field("grant_type") String grantType);
//    Call<AuthResponse> getAuth(@Header("Authorization") String authHeader);

    class Factory {
        private static AuthAPI service;

        public static AuthAPI getInstance() {
            if (service == null) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .addInterceptor(new BasicAuthInterceptor(BuildConfig.ClientID, BuildConfig.ClientSecret))
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .client(client)
                        .build();

                service = retrofit.create(AuthAPI.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
