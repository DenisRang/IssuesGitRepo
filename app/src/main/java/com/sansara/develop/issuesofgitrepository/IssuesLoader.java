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
    private String mOwner;
    private String mRepository;
    private String mState;

    public IssuesLoader(Context context, String owner, String repository, String state) {
        super(context);
        mOwner = owner;
        mRepository = repository;
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
            Response<List<Issue>> response = App.getGithabApi().getIssues(mOwner, mRepository, mState).execute();
            list = response.body();
        } catch (IOException e) {
            Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return list;
    }
}