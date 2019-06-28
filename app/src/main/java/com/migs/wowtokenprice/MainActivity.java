package com.migs.wowtokenprice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.migs.wowtokenprice.api.model.AuthAPI;
import com.migs.wowtokenprice.api.model.AuthResponse;
import com.migs.wowtokenprice.ui.main.AboutActivity;
import com.migs.wowtokenprice.ui.main.RegionPagerAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String WOW_TOKEN_PREFERENCES = "WoWTokenPreferences";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EXPIRES_IN = "expires_in";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        RegionPagerAdapter regionPagerAdapter = new RegionPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(regionPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null || !(networkInfo.isConnected())){
            Toast.makeText(getApplicationContext(),
                    "Error: WoW Token requires a network connection", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        sharedpreferences = getSharedPreferences(WOW_TOKEN_PREFERENCES, Context.MODE_PRIVATE);

        // check if access token is there and
        if (sharedpreferences.contains(EXPIRES_IN)) {
            boolean tokenExpired = true;

            if (tokenExpired) {
                getAuthToken();
            }
        } else {
            getAuthToken();
        }

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_about:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getAuthToken() {
        AuthAPI.Factory.getInstance().getAuth("client_credentials").enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.body() != null) {
                    String accessToken = response.body().getAccess_token();
                    int expiresIn = response.body().getExpires_in();

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(ACCESS_TOKEN, accessToken);
                    editor.putInt(EXPIRES_IN, expiresIn);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Error: WoW Token could not authenticate with Blizzard API", Toast.LENGTH_LONG).show();
            }
        });
    }
}