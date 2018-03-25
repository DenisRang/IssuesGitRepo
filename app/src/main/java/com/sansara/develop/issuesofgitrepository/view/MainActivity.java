package com.sansara.develop.issuesofgitrepository.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sansara.develop.issuesofgitrepository.R;

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
        init();
    }

    private void init(){
        ButterKnife.bind(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, getFragmentManager());
        mViewPagerStates.setAdapter(adapter);
        mTabLayoutStates.setupWithViewPager(mViewPagerStates);
    }
}
