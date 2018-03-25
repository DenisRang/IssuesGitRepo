package com.sansara.develop.issuesofgitrepository.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Comment;
import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.data.Label;
import com.sansara.develop.issuesofgitrepository.model.DetailedIssueModel;
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

public class DetailedIssuePresenter {
    public static final String EXTRA_ISSUE_PARCELABLE = "EXTRA_ISSUE_PARCELABLE";

    private DetailedIssueActivity mView;
    private DetailedIssueModel mModel;

    public DetailedIssuePresenter(DetailedIssueModel model) {
        mModel = model;
    }

    public void attachView(DetailedIssueActivity detailedIssueActivity) {
        mView = detailedIssueActivity;
    }

    public void detachView() {
        mView = null;
    }

    public void viewIsReady() {
        Issue issue = mModel.loadIssue(mView.getIntent());

        mView.setTitleWithNumber(issue.getNumber());

        mView.showIssue(issue);

        ImageView imageViewAvatar = mView.getImageViewAvatar();
        setAvatar(issue.getUser().getAvatarUrl(), imageViewAvatar);

        if (!issue.getLabels().isEmpty()) {
            mView.hideEmptyLabel();
            mView.showLabels(issue.getLabels());
        }

        setCommentsLoaderReset();
        loadComments(issue.getNumber());
    }

    public Issue loadIssue() {
        return mModel.loadIssue(mView.getIntent());
    }

    public void setAvatar(String avatarUrl, ImageView imageViewAvatar) {
        mModel.setAvatar(avatarUrl, imageViewAvatar);
    }

    public TextView createTextViewLabel(Label label) {
        TextView viewLabel = new TextView(mView);
        viewLabel.setText(label.getName());
        viewLabel.setLayoutParams(mView.getTextViewEmptyLabel().getLayoutParams());
        int paggingLabel = mView.getResources().getDimensionPixelSize(R.dimen.label_pagging);
        viewLabel.setPadding(paggingLabel, paggingLabel, paggingLabel, paggingLabel);
        viewLabel.setBackgroundColor(Color.parseColor(label.getFormattedColor()));
        return viewLabel;
    }

    public void loadComments(Integer issueNumber) {
        mModel.loadComments(issueNumber, new DetailedIssueModel.LoadFinishedCallback() {
            @Override
            public void onLoadFinished(Loader<List<Comment>> loader, List<Comment> comments) {
                mView.showComments(comments);
            }
        });
    }

    public void setCommentsLoaderReset() {
        mModel.setCommentsLoaderReset(new DetailedIssueModel.LoaderResetCallback() {
            @Override
            public void onLoaderReset(Loader<List<Comment>> loader) {
                if (mView != null) {
                    mView.clearComments();
                }
            }
        });
    }

}
