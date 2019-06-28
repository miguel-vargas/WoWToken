package com.migs.wowtokenprice.ui.main;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.migs.wowtokenprice.R;
import com.migs.wowtokenprice.api.model.WoWTokenAPI;
import com.migs.wowtokenprice.api.model.WoWTokenResponse;
import com.migs.wowtokenprice.storage.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NAFragment extends Fragment {

    public static final String WOW_TOKEN_PREFERENCES = "WoWTokenPreferences";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String NA_REGION_NAMESPACE = "dynamic-us";

    String accessToken = "";

    SharedPreferences sharedPreferences;

    @BindView(R.id.region) TextView region;

    @BindView(R.id.region_price) TextView regionPrice;

    @BindView(R.id.price_high) TextView priceHigh;

    @BindView(R.id.price_low) TextView priceLow;

    @BindView(R.id.last_date) TextView lastDate;

    @BindView(R.id.last_time) TextView lastTime;

    private Unbinder unbinder;

    public NAFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("https://us.api.blizzard.com/data/wow/token/index")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_region, container, false);

        sharedPreferences = getActivity().getSharedPreferences(WOW_TOKEN_PREFERENCES, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(ACCESS_TOKEN)){
            accessToken = sharedPreferences.getString(ACCESS_TOKEN,"");
        }

        unbinder = ButterKnife.bind(this, rootView);

//        getToken();

        region.setText("North America");

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getToken() {
        if (accessToken.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Error: WoW Token is not authenticated with Blizzard API. Try again.", Toast.LENGTH_LONG).show();
            return;
        }
        accessToken = "Bearer " + accessToken;

        WoWTokenAPI.Factory.getInstance().getWoWToken(accessToken, NA_REGION_NAMESPACE) .enqueue(new Callback<WoWTokenResponse>() {
            @Override
            public void onResponse(Call<WoWTokenResponse> call, Response<WoWTokenResponse> response) {
                if (response.body() != null) {
                    int tokenPrice = response.body().getPriceInGold();
                    regionPrice.setText(tokenPrice);
                }
            }

            @Override
            public void onFailure(Call<WoWTokenResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Error: No NA Response", Toast.LENGTH_LONG).show();
            }
        });
    }

}
