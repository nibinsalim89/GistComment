package com.gist.nibinsalim.gistcomment.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gist.nibinsalim.gistcomment.R;
import com.gist.nibinsalim.gistcomment.retrofit.model.GetCommentsResponse;

import java.util.List;


/**
 * Created by nibinsalim on 13/05/18.
 * Description handles the comment data to be shown in recyclerview
 */


public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private int itemLayout;
    List<GetCommentsResponse> mCommentsList;
    Activity mContext;

    public CommentsAdapter(int itemLayout,List<GetCommentsResponse> commentsList, Activity context) {
        this.itemLayout = itemLayout;
        mCommentsList = commentsList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mComment.setText(mCommentsList.get(position).getBody());
        holder.mCommentedBy.setText("Commented by: "+ mCommentsList.get(position).getUser().getLogin());
    }

    @Override
    public int getItemCount() {
        return mCommentsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mComment, mCommentedBy;


        public ViewHolder(View itemView) {
            super(itemView);
            mComment = (TextView) itemView.findViewById(R.id.tv_comments);
            mCommentedBy = (TextView) itemView.findViewById(R.id.tv_commentedBy);
        }
    }
}
