package com.sansara.develop.issuesofgitrepository;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindDrawable;
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
    @BindView(R.id.text_creator_login)
    TextView mTextViewCreatorLogin;
    @BindView(R.id.text_comments)
    TextView mTextViewComments;

    @BindView(R.id.image_avatar)
    ImageView mImageViewAvatar;
    @BindView(R.id.linear_layout_labels)
    LinearLayout mContainerLabels;
    @BindView(R.id.text_empty_label)
    TextView mTextViewEmptyLabel;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_issue);
        ButterKnife.bind(this);

        Issue issue = (Issue) getIntent().getParcelableExtra(IssuesAdapter.EXTRA_ISSUE_PARCELABLE);

        mTextViewTitle.setText(issue.getTitle());
        mTextViewCreatedAt.append(issue.getFormattedDateCreatedAt());
        mTextViewBody.setText(issue.getBody());
        mTextViewCreatorLogin.append(issue.getUser().getLogin());
        mTextViewComments.setText(issue.getCommentsUrl());

        if (!issue.getLabels().isEmpty()) {
            mTextViewEmptyLabel.setVisibility(View.GONE);
            for (Label label : issue.getLabels()) {

                TextView viewLabel = new TextView(this);
                viewLabel.setText(label.getColor());
                viewLabel.setLayoutParams(mTextViewEmptyLabel.getLayoutParams());
                viewLabel.setPadding(4, 4, 4, 4);
                viewLabel.setBackgroundColor(Color.parseColor(label.getFormattedColor()));
                mContainerLabels.addView(viewLabel);
            }
        }

    }
}

