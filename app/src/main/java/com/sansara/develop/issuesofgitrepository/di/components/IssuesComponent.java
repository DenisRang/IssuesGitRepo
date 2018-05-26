package com.sansara.develop.issuesofgitrepository.di.components;

import com.sansara.develop.issuesofgitrepository.di.modules.IssuesModule;
import com.sansara.develop.issuesofgitrepository.view.IssuesFragment;

import dagger.Component;

@Component(modules = IssuesModule.class, dependencies = ApplicationComponent.class)
public interface IssuesComponent {

    void inject(IssuesFragment issuesFragment);
}
