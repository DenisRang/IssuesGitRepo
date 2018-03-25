package com.sansara.develop.issuesofgitrepository.model;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.view.IssuesFragment;
import com.sansara.develop.issuesofgitrepository.view.MainActivity;

import java.util.List;

/**
 * Created by den on 25.03.2018.
 */

public class IssuesModel implements LoaderManager.LoaderCallbacks<List<Issue>> {
    public static final String RX_QUERY_STATE_OPEN = "open";
    public static final String RX_QUERY_STATE_CLOSED = "closed";

    private static final int OPEN_ISSUES_LOADER_ID = 1;
    private static final int CLOSED_ISSUES_LOADER_ID = 2;

    private String mRxQueryState;
    private Context mContext;
    private LoadFinishedCallback mLoadFinishedCallback;
    private LoaderResetCallback mLoaderResetCallback;

    public IssuesModel(Context context, String rxQueryState) {
        mContext = context;
        mRxQueryState = rxQueryState;
    }

    @Override
    public Loader<List<Issue>> onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case OPEN_ISSUES_LOADER_ID:
                return new IssuesLoader(mContext, RX_QUERY_STATE_OPEN);
            case CLOSED_ISSUES_LOADER_ID:
                return new IssuesLoader(mContext, RX_QUERY_STATE_CLOSED);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Issue>> loader, List<Issue> issues) {
        if (mLoadFinishedCallback != null) {
            mLoadFinishedCallback.onLoadFinished(loader, issues);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Issue>> loader) {
        if (mLoaderResetCallback != null) {
            mLoaderResetCallback.onLoaderReset(loader);
        }
    }

    public void loadIssues(LoadFinishedCallback loadFinishedCallback) {
        mLoadFinishedCallback = loadFinishedCallback;
        LoaderManager loaderManager = ((MainActivity) mContext).getLoaderManager();
        if (mRxQueryState.equals(RX_QUERY_STATE_OPEN))
            loaderManager.initLoader(OPEN_ISSUES_LOADER_ID, null, this);
        else loaderManager.initLoader(CLOSED_ISSUES_LOADER_ID, null, this);
    }

    public void setIssuesLoaderReset(LoaderResetCallback loaderResetCallback) {
        mLoaderResetCallback = loaderResetCallback;
    }

    public interface LoadFinishedCallback {
        void onLoadFinished(Loader<List<Issue>> loader, List<Issue> issues);
    }

    public interface LoaderResetCallback {
        void onLoaderReset(Loader<List<Issue>> loader);
    }
}
