package com.sansara.develop.issuesofgitrepository.interfaces;

import com.sansara.develop.issuesofgitrepository.data.Comment;
import com.sansara.develop.issuesofgitrepository.data.Issue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {
    @GET("/repos/ReactiveX/RxJava/issues")
    Call<List<Issue>> getIssues(@Query("state") String state);

    @GET("/repos/ReactiveX/RxJava/issues/{number}/comments")
    Call<List<Comment>> getComments(@Path("number") Integer number);
}
