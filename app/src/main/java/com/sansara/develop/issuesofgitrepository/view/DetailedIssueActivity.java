package com.sansara.develop.issuesofgitrepository.view;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Comment;
import com.sansara.develop.issuesofgitrepository.data.Issue;
import com.sansara.develop.issuesofgitrepository.data.Label;
import com.sansara.develop.issuesofgitrepository.model.CommentsLoader;
import com.sansara.develop.issuesofgitrepository.model.DetailedIssueModel;
import com.sansara.develop.issuesofgitrepository.presenter.DetailedIssuePresenter;
import com.sansara.develop.issuesofgitrepository.presenter.IssuesPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by den on 24.03.2018.
 */

public class DetailedIssueActivity extends AppCompatActivity {

    @BindView(R.id.text_title_detail)
    TextView mTextViewTitle;
    @BindView(R.id.text_created_at_detail)
    TextView mTextViewCreatedAt;
    @BindView(R.id.text_body_detail)
    TextView mTextViewBody;
    @BindView(R.id.text_login)
    TextView mTextViewLogin;
    @BindView(R.id.image_avatar)
    ImageView mImageViewAvatar;
    @BindView(R.id.linear_layout_labels)
    LinearLayout mContainerLabels;
    @BindView(R.id.text_empty_label)
    TextView mTextViewEmptyLabel;
    @BindView(R.id.text_comments)
    TextView mTextViewComments;

    private DetailedIssuePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_issue);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void init() {
        ButterKnife.bind(this);
        DetailedIssueModel model = new DetailedIssueModel(this);
        mPresenter = new DetailedIssuePresenter(model);
        mPresenter.attachView(this);
        mPresenter.viewIsReady();
    }

    public void setTitleWithNumber(int number) {
        setTitle(getString(R.string.title_detail) + number);
    }

    public void showIssue(Issue issue) {
        mTextViewTitle.setText(issue.getTitle());
        mTextViewCreatedAt.setText(issue.getFormattedDateCreatedAt());
        mTextViewBody.setText(issue.getBody());
        mTextViewLogin.setText(issue.getUser().getLogin());
    }

    public void showLabels(List<Label> labels) {
        for (Label label : labels) {
            TextView viewLabel = new TextView(this);
            viewLabel.setText(label.getName());
            viewLabel.setLayoutParams(mTextViewEmptyLabel.getLayoutParams());
            int paggingLabel = getResources().getDimensionPixelSize(R.dimen.label_pagging);
            viewLabel.setPadding(paggingLabel, paggingLabel, paggingLabel, paggingLabel);
            viewLabel.setBackgroundColor(Color.parseColor(label.getFormattedColor()));
            mContainerLabels.addView(viewLabel);
        }
    }

    public void showComments(List<Comment> comments) {
        mTextViewComments.setText("");
        if (comments != null && !comments.isEmpty()) {
            for (Comment comment : comments) {
                mTextViewComments.append(">>>  " + comment.getUser().getLogin() + "\n");
                mTextViewComments.append(comment.getBody() + "\n\n\n");
            }
        }
    }

    public ImageView getImageViewAvatar() {
        return mImageViewAvatar;
    }

    public void hideEmptyLabel() {
        mTextViewEmptyLabel.setVisibility(View.GONE);
    }

    public void clearComments() {
        mTextViewComments.setText("");
    }
}

