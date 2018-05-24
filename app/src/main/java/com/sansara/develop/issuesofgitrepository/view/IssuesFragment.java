package com.sansara.develop.issuesofgitrepository.view;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.model.IssuesLoader;
import com.sansara.develop.issuesofgitrepository.model.IssuesModel;
import com.sansara.develop.issuesofgitrepository.presenter.IssuesPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by den on 22.03.2018.
 */

public class IssuesFragment extends Fragment {
    private static final String BUNDLE_RX_QUERY_STATE = "BUNDLE_RX_QUERY_STATE";

    @BindView(R.id.recycler_issues)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty_view)
    TextView mEmptyView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private Unbinder mUnbinder;
    private IssuesAdapter mIssuesAdapter;
    private IssuesPresenter mPresenter;

    public IssuesFragment() {
    }

    public static IssuesFragment newInstance(String rxQueryState) {
        IssuesFragment fragment = new IssuesFragment();

        Bundle args = new Bundle();
        args.putString(BUNDLE_RX_QUERY_STATE, rxQueryState);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issues, container, false);

        init(rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.detachView();
    }

    private void init(View rootView) {
        IssuesModel model = new IssuesModel(getActivity(), getRxQueryState());
        mPresenter = new IssuesPresenter(model);
        mPresenter.attachView(this);
        mPresenter.viewIsReady();

        mUnbinder = ButterKnife.bind(this, rootView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mIssuesAdapter = new IssuesAdapter(mPresenter.getIssues(), new IssuesAdapter.IssueClickListener() {
            @Override
            public void onIssueClick(int position) {
                mPresenter.onIssueClick(position);
            }
        });
        mRecyclerView.setAdapter(mIssuesAdapter);
    }

    public void showNoInternetConnection() {
        mEmptyView.setText(R.string.msg_no_internet);
    }

    public void showIssues() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void showNoIssues() {
        mEmptyView.setText(R.string.msg_no_issues);
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    public String getRxQueryState() {
        return getArguments().getString(BUNDLE_RX_QUERY_STATE);
    }
}
