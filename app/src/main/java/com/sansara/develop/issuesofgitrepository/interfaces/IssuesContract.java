package com.sansara.develop.issuesofgitrepository.interfaces;

import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.model.IssuesModel;

import java.util.List;

public interface IssuesContract {

    interface View {

        void showNoInternetConnection();

        void showIssues();

        void showNoIssues();

        void showProgressBar();

        void hideProgressBar();

    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void loadIssues();

        List<Issue> getIssues();

        void onIssueClick(int position);

    }

    interface Model {

        void loadIssues(IssuesModel.LoadFinishedCallback loadFinishedCallback);

        List<Issue> getIssues();

        boolean isEmpty();

        void clearIssues();

        void setIssuesLoaderReset(IssuesModel.LoaderResetCallback loaderResetCallback);
    }

}
