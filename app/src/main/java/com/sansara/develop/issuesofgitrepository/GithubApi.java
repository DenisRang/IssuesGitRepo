package com.sansara.develop.issuesofgitrepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by den on 21.03.2018.
 */

public interface GithubApi {
    @GET("/repos/{owner}/{repo}/issues")
    Call<List<Issue>> getIssues(@Path("owner") String owner, @Path("repo") String repo, @Query("state") String state);
}
