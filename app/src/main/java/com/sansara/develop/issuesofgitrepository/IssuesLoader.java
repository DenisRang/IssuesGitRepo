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
        final List<Issue> list=new ArrayList<Issue>();      //TODO try null instead ArrayList
        App.getGithabApi().getIssues(mOwner, mRepository, mState).enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                list.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }

        });
        return list;
    }
}