package com.sansara.develop.issuesofgitrepository;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by den on 22.03.2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final int COUNT_STATES_OF_ISSUE = 2;

    @BindString(R.string.state_open)
    String stateOpen;
    @BindString(R.string.state_closed)
    String stateClosed;


    public ViewPagerAdapter(Activity activity, FragmentManager fm) {
        super(fm);
        ButterKnife.bind(this, activity);
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
                return stateOpen;
            case 1:
                return stateClosed;
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT_STATES_OF_ISSUE;
    }
}
