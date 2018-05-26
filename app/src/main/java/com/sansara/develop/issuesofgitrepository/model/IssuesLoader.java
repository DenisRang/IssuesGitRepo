package com.sansara.develop.issuesofgitrepository.model;

/**
 * Created by den on 21.03.2018.
 */


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Issue;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesLoader extends AsyncTaskLoader<List<Issue>> {
    private String state;

    public IssuesLoader(Context context, String state) {
        super(context);
        this.state = state;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Issue> loadInBackground() {
        List<Issue> list = null;
        try {
            Response<List<Issue>> response = App.getGithubApi().getIssues(state).execute();
            list = response.body();
        } catch (IOException e) {
            Toast.makeText(getContext(), getContext().getString(R.string.error_loading_issues), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return list;
    }
}