package com.sansara.develop.issuesofgitrepository.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sansara.develop.issuesofgitrepository.R;
import com.sansara.develop.issuesofgitrepository.data.Issue;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by den on 21.03.2018.
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {

    private List<Issue> issues;
    private IssueClickListener issueClickListener;

    public IssuesAdapter(List<Issue> issues, IssueClickListener issueClickListener) {
        this.issues = issues;
        this.issueClickListener = issueClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_issue, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Issue issue = issues.get(position);

        holder.textViewTitle.setText(issue.getTitle());
        holder.textViewCreatedAt.setText(issue.getFormattedDateCreatedAt());
        holder.textViewBody.setText(issue.getBody());
    }

    @Override
    public int getItemCount() {
        if (issues == null)
            return 0;
        return issues.size();
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
                issueClickListener.onIssueClick(position);
            }
        }
    }

    public interface IssueClickListener {
        void onIssueClick(int position);
    }
}
