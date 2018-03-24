package com.sansara.develop.issuesofgitrepository;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by den on 22.03.2018.
 */

public class IssuesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Issue>> {
    public static final String RX_QUERY_STATE_OPEN = "open";
    public static final String RX_QUERY_STATE_CLOSED = "closed";

    private static final String BUNDLE_RX_QUERY_STATE = "rx_query_state";
    private static final int OPEN_ISSUES_LOADER_ID = 1;
    private static final int CLOSED_ISSUES_LOADER_ID = 2;

    @BindView(R.id.recycler_issues)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty_view)
    TextView mEmptyView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    private List<Issue> mIssues;
    private IssuesAdapter mIssuesAdapter;
    private Unbinder mUnbinder;

    public IssuesFragment() {
    }

    public static IssuesFragment newInstance(String rxQueryState) {
        IssuesFragment fragment = new IssuesFragment();

        Bundle args = new Bundle();
        args.putString(BUNDLE_RX_QUERY_STATE, rxQueryState);
        fragment.setArguments(args);

        return fragment;
    }

    public String getRxQueryState() {
        return getArguments().getString(BUNDLE_RX_QUERY_STATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issues, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        mIssues = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mIssuesAdapter = new IssuesAdapter(getActivity(),mIssues);
        mRecyclerView.setAdapter(mIssuesAdapter);

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            if (getRxQueryState().equals(RX_QUERY_STATE_OPEN))
                loaderManager.initLoader(OPEN_ISSUES_LOADER_ID, null, this);
            else loaderManager.initLoader(CLOSED_ISSUES_LOADER_ID, null, this);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyView.setText(R.string.msg_no_internet);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public Loader<List<Issue>> onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case OPEN_ISSUES_LOADER_ID:
                return new IssuesLoader(getActivity(), RX_QUERY_STATE_OPEN);
            case CLOSED_ISSUES_LOADER_ID:
                return new IssuesLoader(getActivity(), RX_QUERY_STATE_CLOSED);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Issue>> loader, List<Issue> issues) {
        mIssues.clear();
        mProgressBar.setVisibility(View.GONE);
        if (issues != null && !issues.isEmpty()) {
            mIssues.addAll(issues);
        }
        if (mIssues.isEmpty()) {
            mEmptyView.setText(R.string.msg_no_issues);
        } else {
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Issue>> loader) {
        mIssues.clear();
    }
}
