package com.sansara.develop.issuesofgitrepository.model;

import android.app.Application;

import com.sansara.develop.issuesofgitrepository.data.Comment;
import com.sansara.develop.issuesofgitrepository.data.Issue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    public interface GithubApi {
        @GET("/repos/ReactiveX/RxJava/issues")
        Call<List<Issue>> getIssues(@Query("state") String state);

        @GET("/repos/ReactiveX/RxJava/issues/{number}/comments")
        Call<List<Comment>> getComments(@Path("number") Integer number);
    }
}
