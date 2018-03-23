package com.sansara.develop.issuesofgitrepository;

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
    private static final String LOG_TAG = IssuesAdapter.class.getSimpleName();

    private List<Issue> mIssues;

    public IssuesAdapter(List<Issue> issues) {
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

        holder.commentsUrl = issue.getCommentsUrl();
        holder.title = issue.getTitle();
        holder.state = issue.getState();
        holder.body = issue.getBody();
        holder.createdAt = getFormattedDate(issue.getCreatedAt());

        holder.textViewTitle.setText(holder.title);
        holder.textViewCreatedAt.setText(holder.createdAt);
        holder.textViewBody.setText(holder.body);
    }

    @Override
    public int getItemCount() {
        if (mIssues == null)
            return 0;
        return mIssues.size();
    }

    private String getFormattedDate(String inDateString) {
        String outDateString = null;
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("y-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            SimpleDateFormat outFormat = new SimpleDateFormat("dd.MM.y'\n'HH:mm", Locale.US);

            Date outDate = inFormat.parse(inDateString);
            outDateString = outFormat.format(outDate);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error with parsing date");
            e.printStackTrace();
        }
        return outDateString;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        String commentsUrl, title, state, body, createdAt;
        User user;
        List<Label> labels;

        @BindView(R.id.text_title)
        TextView textViewTitle;
        @BindView(R.id.text_created_at)
        TextView textViewCreatedAt;
        @BindView(R.id.text_body)
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
                //TODO
            }
        }
    }
}
