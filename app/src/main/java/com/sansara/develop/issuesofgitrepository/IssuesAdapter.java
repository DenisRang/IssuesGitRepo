package com.sansara.develop.issuesofgitrepository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by den on 21.03.2018.
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {
    public static final String EXTRA_ISSUE_PARCELABLE = "EXTRA_ISSUE_PARCELABLE";

    private List<Issue> mIssues;
    private Activity mActivity;

    public IssuesAdapter(Activity activity, List<Issue> issues) {
        mActivity = activity;
        mIssues = issues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_issue, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Issue issue = mIssues.get(position);

        holder.textViewTitle.setText(issue.getTitle());
        holder.textViewCreatedAt.setText(issue.getFormattedDateCreatedAt());
        holder.textViewBody.setText(issue.getBody());
    }

    @Override
    public int getItemCount() {
        if (mIssues == null)
            return 0;
        return mIssues.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_title_item)
        TextView textViewTitle;
        @BindView(R.id.text_created_at_item)
        TextView textViewCreatedAt;
        @BindView(R.id.text_body_item)
        TextView textViewBody;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(mActivity, DetailedIssueActivity.class);
                intent.putExtra(EXTRA_ISSUE_PARCELABLE, mIssues.get(position));
                mActivity.startActivity(intent);
            }
        }
    }
}
