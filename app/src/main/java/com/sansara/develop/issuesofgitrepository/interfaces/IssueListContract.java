package com.sansara.develop.issuesofgitrepository.interfaces;

import com.sansara.develop.issuesofgitrepository.data.Issue;

import java.util.List;

public interface IssueListContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     */
    interface Presenter {

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Issue> issueList);

        void onResponseFailure(Throwable throwable);

    }
}