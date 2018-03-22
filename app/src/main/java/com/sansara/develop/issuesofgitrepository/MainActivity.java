package com.sansara.develop.issuesofgitrepository;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tab_layout_states)
    TabLayout mTabLayoutStates;
    @BindView(R.id.pager_states)
    ViewPager mViewPagerStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, getFragmentManager());
        mViewPagerStates.setAdapter(adapter);
        mTabLayoutStates.setupWithViewPager(mViewPagerStates);
    }
}
