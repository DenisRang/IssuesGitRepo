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
import java.util.List;
import java.util.Locale;

/**
 * Created by den on 21.03.2018.
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {
    private static final String LOG_TAG = IssuesAdapter.class.getSimpleName();

    private List<Issue> mIssues;
    private SimpleDateFormat mInFormat = new SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SS'Z'", Locale.US);
    private SimpleDateFormat mOutFormat = new SimpleDateFormat("DD.MM.YYYY'\n'HH:MM", Locale.US);

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
        try {
            holder.createdAt = mOutFormat.format(mInFormat.parse(issue.getCreatedAt()));
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error with parsing date");
            e.printStackTrace();
        }

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

    class ViewHolder extends RecyclerView.ViewHolder {
        String commentsUrl, title, state, body, createdAt;
        User user;
        List<Label> labels;
        TextView textViewTitle, textViewCreatedAt, textViewBody;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.text_title);
            textViewCreatedAt = (TextView) itemView.findViewById(R.id.text_created_at);
            textViewBody = (TextView) itemView.findViewById(R.id.text_body);
        }
    }
}
