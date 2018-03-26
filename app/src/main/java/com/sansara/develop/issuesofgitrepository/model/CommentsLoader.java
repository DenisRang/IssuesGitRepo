package com.sansara.develop.issuesofgitrepository.model;

/**
 * Created by den on 24.03.2018.
 */


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.widget.Toast;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Comment;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class CommentsLoader extends AsyncTaskLoader<List<Comment>> {
    Integer mNumber;

    public CommentsLoader(Context context,Integer number) {
        super(context);
        mNumber=number;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Comment> loadInBackground() {
        List<Comment> list = null;
        try {
            Response<List<Comment>> response = App.getGithabApi().getComments(mNumber).execute();
            list = response.body();
        } catch (IOException e) {
            Toast.makeText(getContext(), getContext().getString(R.string.error_loading_comments), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return list;
    }
}