package com.sansara.develop.issuesofgitrepository;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;


/**
 * Created by den on 22.03.2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final int COUNT_STATES_OF_ISSUE = 2;


    private Context mContext;


    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return IssuesFragment.newInstance(IssuesFragment.RX_QUERY_STATE_OPEN);
            case 1:
                return IssuesFragment.newInstance(IssuesFragment.RX_QUERY_STATE_CLOSED);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.state_open);
            case 1:
                return mContext.getString(R.string.state_closed);
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT_STATES_OF_ISSUE;
    }
}
