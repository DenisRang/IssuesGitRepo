package com.sansara.develop.issuesofgitrepository;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Issue>> {
    private static final String RX_OWNER = "ReactiveX";
    private static final String RX_REPOSITORY = "RxJava";
    private static final String RX_QUERY_STATE_ALL = "all";
    private static final int ISSUES_LOADER_ID = 1;

    private RecyclerView mRecyclerView;
    private List<Issue> mIssues;
    private IssuesAdapter mIssuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIssues = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_issues);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mIssuesAdapter = new IssuesAdapter(mIssues);
        mRecyclerView.setAdapter(mIssuesAdapter);

        getLoaderManager().initLoader(ISSUES_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<Issue>> onCreateLoader(int i, Bundle bundle) {
        return new IssuesLoader(this, RX_OWNER, RX_REPOSITORY, RX_QUERY_STATE_ALL);
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
