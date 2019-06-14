package com.migs.wowtokenprice.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.migs.wowtokenprice.R;

public class RegionPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public RegionPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NAFragment();
            case 1:
                return new EUFragment();
            case 2:
                return new CNFragment();
            case 3:
                return new TWFragment();
            case 4:
                return new KRFragment();
            default:
                return new NAFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.na);
            case 1:
                return mContext.getString(R.string.eu);
            case 2:
                return mContext.getString(R.string.cn);
            case 3:
                return mContext.getString(R.string.tw);
            case 4:
                return mContext.getString(R.string.kr);
            default:
                return mContext.getString(R.string.default_region);
        }
    }
}
