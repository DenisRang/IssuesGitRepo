package com.sansara.develop.issuesofgitrepository.di.modules;

import android.content.Context;

import com.sansara.develop.issuesofgitrepository.interfaces.IssuesContract;
import com.sansara.develop.issuesofgitrepository.model.IssuesModel;
import com.sansara.develop.issuesofgitrepository.presenter.IssuesPresenter;
import com.sansara.develop.issuesofgitrepository.view.IssuesAdapter;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class IssuesModule {
    private String state;
    private Context context;

    public IssuesModule(Context context, String state) {
        this.context = context;
        this.state = state;
    }

    @Provides
    IssuesContract.Presenter presenter(IssuesContract.Model model) {
        return new IssuesPresenter(model);
    }

    @Provides
    IssuesContract.Model model() {
        return new IssuesModel(context, state);
    }


}
