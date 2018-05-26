package com.sansara.develop.issuesofgitrepository.di.components;

import android.content.Context;

import com.sansara.develop.issuesofgitrepository.di.modules.GithubApiModule;
import com.sansara.develop.issuesofgitrepository.di.modules.IssuesModule;
import com.sansara.develop.issuesofgitrepository.di.modules.PicassoModule;
import com.sansara.develop.issuesofgitrepository.interfaces.GithubApi;
import com.sansara.develop.issuesofgitrepository.view.IssuesFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {PicassoModule.class, GithubApiModule.class})
public interface ApplicationComponent {

    GithubApi getGithubApi();

}
