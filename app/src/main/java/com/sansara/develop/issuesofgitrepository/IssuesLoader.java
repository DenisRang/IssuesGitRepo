package com.sansara.develop.issuesofgitrepository;

/**
 * Created by den on 21.03.2018.
 */


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesLoader extends AsyncTaskLoader<List<Issue>> {
    private String mState;

    public IssuesLoader(Context context, String state) {
        super(context);
        mState = state;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Issue> loadInBackground() {
        List<Issue> list = null;
        try {
            Response<List<Issue>> response = App.getGithabApi().getIssues(mState).execute();
            list = response.body();
        } catch (IOException e) {
            Toast.makeText(getContext(), getContext().getString(R.string.error_loading_issues), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return list;
    }
}