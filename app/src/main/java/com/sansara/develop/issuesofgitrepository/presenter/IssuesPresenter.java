package com.sansara.develop.issuesofgitrepository.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.interfaces.IssuesContract;
import com.sansara.develop.issuesofgitrepository.model.IssuesModel;
import com.sansara.develop.issuesofgitrepository.view.DetailedIssueActivity;
import com.sansara.develop.issuesofgitrepository.view.IssuesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 25.03.2018.
 */

public class IssuesPresenter implements IssuesContract.Presenter {
    public static final String EXTRA_ISSUE_PARCELABLE = "EXTRA_ISSUE_PARCELABLE";

    @Nullable
    private IssuesContract.View view;
    private IssuesContract.Model model;


    public IssuesPresenter(IssuesContract.Model model) {
        this.model = model;
    }

    @Override
    public void attachView(IssuesContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadIssues() {
        setLoaderReset();

        ConnectivityManager cm = (ConnectivityManager) ((IssuesFragment) view).getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            model.loadIssues(new IssuesModel.LoadFinishedCallback() {
                @Override
                public void onLoadFinished() {
                    view.hideProgressBar();
                    if (model.isEmpty()) {
                        view.showNoIssues();
                    } else {
                        view.showIssues();
                    }
                }
            });
        } else {
            view.hideProgressBar();
            view.showNoInternetConnection();
        }
    }

    @Override
    public List<Issue> getIssues() {
        return model.getIssues();
    }

    @Override
    public void onIssueClick(int position) {
        Issue currentIssue = model.getIssues().get(position);
        if (currentIssue != null) {
            Intent intent = new Intent(((IssuesFragment) view).getActivity(), DetailedIssueActivity.class);
            intent.putExtra(EXTRA_ISSUE_PARCELABLE, currentIssue);
            ((IssuesFragment) view).startActivity(intent);
        }
    }

    private void setLoaderReset() {
        model.setIssuesLoaderReset(new IssuesModel.LoaderResetCallback() {
            @Override
            public void onLoaderReset() {
                model.clearIssues();
            }
        });
    }

}
