package com.sansara.develop.issuesofgitrepository.view;

import android.app.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.di.components.DaggerIssuesComponent;
import com.sansara.develop.issuesofgitrepository.di.components.IssuesComponent;
import com.sansara.develop.issuesofgitrepository.di.modules.IssuesModule;
import com.sansara.develop.issuesofgitrepository.interfaces.IssuesContract;
import com.sansara.develop.issuesofgitrepository.model.App;
import com.sansara.develop.issuesofgitrepository.model.IssuesModel;
import com.sansara.develop.issuesofgitrepository.presenter.IssuesPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by den on 22.03.2018.
 */

public class IssuesFragment extends Fragment implements IssuesContract.View {
    private static final String BUNDLE_ISSUE_STATE = "BUNDLE_ISSUE_STATE";

    @BindView(R.id.recycler_issues)
    RecyclerView recyclerView;
    @BindView(R.id.text_empty_view)
    TextView emptyView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Unbinder unbinder;

    @Inject
    IssuesAdapter issuesAdapter;
    @Inject
    IssuesContract.Presenter presenter;

    public IssuesFragment() {
    }

    public static IssuesFragment newInstance(String rxQueryState) {
        IssuesFragment fragment = new IssuesFragment();

        Bundle args = new Bundle();
        args.putString(BUNDLE_ISSUE_STATE, rxQueryState);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issues, container, false);
        afterActivityLevelComponent();
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.loadIssues();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @Override
    public void showNoInternetConnection() {
        emptyView.setText(R.string.msg_no_internet);
    }

    @Override
    public void showIssues() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showNoIssues() {
        emptyView.setText(R.string.msg_no_issues);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void initViews(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(issuesAdapter);
    }

    private void afterActivityLevelComponent() {
        IssuesComponent issuesComponent= DaggerIssuesComponent.builder()
                .issuesModule(new IssuesModule(getActivity(),getIssueState()))
                .applicationComponent(((App)getActivity().getApplication()).getComponent())
                .build();

       issuesComponent.inject(this);
    }

    private String getIssueState() {
        return getArguments().getString(BUNDLE_ISSUE_STATE);
    }

}
