package com.migs.wowtokenprice.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.migs.wowtokenprice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class KRFragment extends Fragment {

    @BindView(R.id.region) TextView region;

    @BindView(R.id.region_price) TextView regionPrice;

    @BindView(R.id.price_high) TextView priceHigh;

    @BindView(R.id.price_low) TextView priceLow;

    @BindView(R.id.last_date) TextView lastDate;

    @BindView(R.id.last_time) TextView lastTime;

    private Unbinder unbinder;

    public KRFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_region, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        priceLow.setText("Korea");

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
