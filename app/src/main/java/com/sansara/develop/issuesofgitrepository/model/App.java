package com.sansara.develop.issuesofgitrepository.model;

import android.app.Application;
import android.support.annotation.NonNull;

import com.sansara.develop.issuesofgitrepository.data.Comment;
import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.di.components.ApplicationComponent;
import com.sansara.develop.issuesofgitrepository.di.components.DaggerApplicationComponent;
import com.sansara.develop.issuesofgitrepository.di.modules.ContextModule;
import com.sansara.develop.issuesofgitrepository.di.modules.IssuesModule;
import com.sansara.develop.issuesofgitrepository.interfaces.GithubApi;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import timber.log.Timber;

/**
 * Created by den on 21.03.2018.
 */

public class App extends Application {
    private static GithubApi githubApi;         //TODO temp

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this.getApplicationContext()))
                .build();
        githubApi = component.getGithubApi();

    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public static GithubApi getGithubApi() {
        return githubApi;
    }

}
