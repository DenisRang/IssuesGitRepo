package com.sansara.develop.issuesofgitrepository.model;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.interfaces.IssuesContract;
import com.sansara.develop.issuesofgitrepository.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;

/**
 * Created by den on 25.03.2018.
 */

public class IssuesModel implements IssuesContract.Model, LoaderManager.LoaderCallbacks<List<Issue>> {
    public static final String RX_QUERY_STATE_OPEN = "open";
    public static final String RX_QUERY_STATE_CLOSED = "closed";
    private static final int OPEN_ISSUES_LOADER_ID = 1;
    private static final int CLOSED_ISSUES_LOADER_ID = 2;

    private String state;
    Context context;

    private LoadFinishedCallback loadFinishedCallback;
    private LoaderResetCallback loaderResetCallback;
    private List<Issue> issues;

    public IssuesModel(Context context, String state) {
        this.context = context;
        this.state = state;
        issues = new ArrayList<Issue>();
    }

    @Override
    public Loader<List<Issue>> onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case OPEN_ISSUES_LOADER_ID:
                return new IssuesLoader(context, RX_QUERY_STATE_OPEN);
            case CLOSED_ISSUES_LOADER_ID:
                return new IssuesLoader(context, RX_QUERY_STATE_CLOSED);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Issue>> loader, List<Issue> issues) {
        this.issues.clear();
        this.issues.addAll(issues);
        if (loadFinishedCallback != null) {
            loadFinishedCallback.onLoadFinished();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Issue>> loader) {
        if (loaderResetCallback != null) {
            loaderResetCallback.onLoaderReset();
        }
    }

    @Override
    public void loadIssues(LoadFinishedCallback loadFinishedCallback) {
        this.loadFinishedCallback = loadFinishedCallback;
        LoaderManager loaderManager = ((Activity) context).getLoaderManager();
        if (state.equals(RX_QUERY_STATE_OPEN))
            loaderManager.initLoader(OPEN_ISSUES_LOADER_ID, null, this);
        else loaderManager.initLoader(CLOSED_ISSUES_LOADER_ID, null, this);
    }

    @Override
    public void clearIssues() {
        issues.clear();
    }

    @Override
    public List<Issue> getIssues() {
        return issues;
    }

    @Override
    public boolean isEmpty() {
        return issues == null || issues.isEmpty();
    }

    @Override
    public void setIssuesLoaderReset(LoaderResetCallback loaderResetCallback) {
        this.loaderResetCallback = loaderResetCallback;
    }

    public interface LoadFinishedCallback {
        void onLoadFinished();
    }

    public interface LoaderResetCallback {
        void onLoaderReset();
    }
}
