package com.sansara.develop.issuesofgitrepository;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by den on 22.03.2018.
 */

public class IssuesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Issue>> {
    public static final String RX_OWNER = "ReactiveX";
    public static final String RX_REPOSITORY = "RxJava";
    public static final String RX_QUERY_STATE_OPEN = "open";
    public static final String RX_QUERY_STATE_CLOSED = "closed";

    private static final String BUNDLE_RX_QUERY_STATE = "rx_query_state";
    private static final int OPEN_ISSUES_LOADER_ID = 1;
    private static final int CLOSED_ISSUES_LOADER_ID = 2;

    private RecyclerView mRecyclerView;
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

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issues, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        mIssues = new ArrayList<>();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_issues);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mIssuesAdapter = new IssuesAdapter(mIssues);
        mRecyclerView.setAdapter(mIssuesAdapter);

        String rxQueryState = getArguments().getString(BUNDLE_RX_QUERY_STATE);
        if (rxQueryState.equals(RX_QUERY_STATE_OPEN))
            getLoaderManager().initLoader(OPEN_ISSUES_LOADER_ID, null, this);
        else getLoaderManager().initLoader(CLOSED_ISSUES_LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public Loader<List<Issue>> onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case OPEN_ISSUES_LOADER_ID:
                return new IssuesLoader(getActivity(), RX_OWNER, RX_REPOSITORY, RX_QUERY_STATE_OPEN);
            case CLOSED_ISSUES_LOADER_ID:
                return new IssuesLoader(getActivity(), RX_OWNER, RX_REPOSITORY, RX_QUERY_STATE_CLOSED);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Issue>> loader, List<Issue> issues) {
        mIssues.clear();
        if (issues != null && !issues.isEmpty()) {
            mIssues.addAll(issues);
        }
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Issue>> loader) {
        mIssues.clear();
    }
}
