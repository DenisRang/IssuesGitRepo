package com.sansara.develop.issuesofgitrepository.di.modules;

import com.sansara.develop.issuesofgitrepository.interfaces.GithubApi;
import com.sansara.develop.issuesofgitrepository.model.App;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class GithubApiModule {

    @Provides
    GithubApi githubApi(Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }

    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())     // For converting JSON to an Object
                .build();
    }
}
