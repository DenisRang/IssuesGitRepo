package com.sansara.develop.issuesofgitrepository;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by den on 21.03.2018.
 */

public class App extends Application {
    private static GithubApi mGithubApi;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())     // For converting JSON to an Object
                .build();

        mGithubApi = mRetrofit.create(GithubApi.class);
    }

    public static GithubApi getGithabApi() {
        return mGithubApi;
    }
}
