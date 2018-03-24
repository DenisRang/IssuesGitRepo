package com.sansara.develop.issuesofgitrepository;

import android.app.LoaderManager;
import android.content.Loader;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by den on 24.03.2018.
 */

public class DetailedIssueActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Comment>> {
    private static final int COMMENTS_LOADER_ID = 3;

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

    private Integer mNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_issue);
        ButterKnife.bind(this);

        Issue issue = (Issue) getIntent().getParcelableExtra(IssuesAdapter.EXTRA_ISSUE_PARCELABLE);

        mNumber = issue.getNumber();
        setTitle(getString(R.string.title_detail) + mNumber);
        mTextViewTitle.setText(issue.getTitle());
        mTextViewCreatedAt.setText(issue.getFormattedDateCreatedAt());
        mTextViewBody.setText(issue.getBody());
        Picasso.get().load(issue.getUser().getAvatarUrl())
                .resizeDimen(R.dimen.avatar, R.dimen.avatar)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(mImageViewAvatar);
        mTextViewLogin.setText(issue.getUser().getLogin());

        if (!issue.getLabels().isEmpty()) {
            mTextViewEmptyLabel.setVisibility(View.GONE);
            for (Label label : issue.getLabels()) {

                TextView viewLabel = new TextView(this);
                viewLabel.setText(label.getName());
                viewLabel.setLayoutParams(mTextViewEmptyLabel.getLayoutParams());
                int paggingLabel = getResources().getDimensionPixelSize(R.dimen.label_pagging);
                viewLabel.setPadding(paggingLabel, paggingLabel, paggingLabel, paggingLabel);
                viewLabel.setBackgroundColor(Color.parseColor(label.getFormattedColor()));
                mContainerLabels.addView(viewLabel);
            }
        }

        getLoaderManager().initLoader(COMMENTS_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Comment>> onCreateLoader(int i, Bundle bundle) {
        return new CommentsLoader(this, mNumber);
    }

    @Override
    public void onLoadFinished(Loader<List<Comment>> loader, List<Comment> comments) {
        mTextViewComments.setText("");
        if (comments != null && !comments.isEmpty()) {
            for (Comment comment : comments) {
                mTextViewComments.append(">>>  " + comment.getUser().getLogin() + "\n");
                mTextViewComments.append(comment.getBody() + "\n\n\n");
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Comment>> loader) {
        mTextViewComments.setText("");
    }
}

