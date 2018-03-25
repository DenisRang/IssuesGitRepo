package com.sansara.develop.issuesofgitrepository.model;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ImageView;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Comment;
import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.presenter.IssuesPresenter;
import com.sansara.develop.issuesofgitrepository.view.DetailedIssueActivity;
import com.sansara.develop.issuesofgitrepository.view.IssuesFragment;
import com.sansara.develop.issuesofgitrepository.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by den on 25.03.2018.
 */

public class DetailedIssueModel implements LoaderManager.LoaderCallbacks<List<Comment>> {
    private static final int COMMENTS_LOADER_ID = 3;
    private static final String BUNDLE_ISSUE_NUMBER = "BUNDLE_ISSUE_NUMBER";

    private Context mContext;
    private LoadFinishedCallback mLoadFinishedCallback;
    private LoaderResetCallback mLoaderResetCallback;

    public DetailedIssueModel(Context context) {
        mContext = context;
    }

    @Override
    public Loader<List<Comment>> onCreateLoader(int i, Bundle bundle) {
        return new CommentsLoader(mContext, bundle.getInt(BUNDLE_ISSUE_NUMBER));
    }

    @Override
    public void onLoadFinished(Loader<List<Comment>> loader, List<Comment> issues) {
        if (mLoadFinishedCallback != null) {
            mLoadFinishedCallback.onLoadFinished(loader, issues);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Comment>> loader) {
        if (mLoaderResetCallback != null) {
            mLoaderResetCallback.onLoaderReset(loader);
        }
    }

    public Issue loadIssue(Intent intent) {
        return (Issue) intent.getParcelableExtra(IssuesPresenter.EXTRA_ISSUE_PARCELABLE);
    }

    public void setAvatar(String avatarUrl, ImageView imageViewAvatar) {
        Picasso.get().load(avatarUrl)
                .resizeDimen(R.dimen.avatar, R.dimen.avatar)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imageViewAvatar);
    }

    public void loadComments(Integer issueNumber, LoadFinishedCallback loadFinishedCallback) {
        mLoadFinishedCallback = loadFinishedCallback;
        LoaderManager loaderManager = ((DetailedIssueActivity) mContext).getLoaderManager();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_ISSUE_NUMBER, issueNumber);
        loaderManager.initLoader(COMMENTS_LOADER_ID, bundle, this);
    }

    public void setCommentsLoaderReset(LoaderResetCallback loaderResetCallback) {
        mLoaderResetCallback = loaderResetCallback;
    }

    public interface LoadFinishedCallback {
        void onLoadFinished(Loader<List<Comment>> loader, List<Comment> issues);
    }

    public interface LoaderResetCallback {
        void onLoaderReset(Loader<List<Comment>> loader);
    }
}
