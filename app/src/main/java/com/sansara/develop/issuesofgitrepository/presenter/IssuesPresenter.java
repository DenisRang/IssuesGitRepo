package com.sansara.develop.issuesofgitrepository.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.model.IssuesLoader;
import com.sansara.develop.issuesofgitrepository.model.IssuesModel;
import com.sansara.develop.issuesofgitrepository.view.DetailedIssueActivity;
import com.sansara.develop.issuesofgitrepository.view.IssuesAdapter;
import com.sansara.develop.issuesofgitrepository.view.IssuesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 25.03.2018.
 */

public class IssuesPresenter {
    public static final String EXTRA_ISSUE_PARCELABLE = "EXTRA_ISSUE_PARCELABLE";

    private IssuesFragment mView;
    private IssuesModel mModel;
    private List<Issue> mIssues;


    public IssuesPresenter(IssuesModel model) {
        mModel = model;
        mIssues = new ArrayList<Issue>();
    }


    public void attachView(IssuesFragment issuesFragment) {
        mView = issuesFragment;
    }

    public void detachView() {
        mView = null;
    }

    public void viewIsReady() {
        setLoaderReset();
        loadIssues();
    }

    public List<Issue> getIssues() {
        return mIssues;
    }

    public void onIssueClick(int position) {
        Intent intent = new Intent(mView.getActivity(), DetailedIssueActivity.class);
        intent.putExtra(EXTRA_ISSUE_PARCELABLE, mIssues.get(position));
        mView.startActivity(intent);
    }

    public void loadIssues() {
        ConnectivityManager cm = (ConnectivityManager) mView.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            mModel.loadIssues(new IssuesModel.LoadFinishedCallback() {
                @Override
                public void onLoadFinished(Loader<List<Issue>> loader, List<Issue> issues) {
                    mView.hideProgressBar();

                    mIssues.clear();
                    if (issues != null && !issues.isEmpty()) mIssues.addAll(issues);

                    mView.showIssues();

                    if (mIssues.isEmpty()) mView.showNoIssues();
                }
            });
        } else {
            mView.hideProgressBar();
            mView.showNoInternetConnection();
        }
    }

    public void setLoaderReset() {
        mModel.setIssuesLoaderReset(new IssuesModel.LoaderResetCallback() {
            @Override
            public void onLoaderReset(Loader<List<Issue>> loader) {
                mIssues.clear();
            }
        });
    }

}
