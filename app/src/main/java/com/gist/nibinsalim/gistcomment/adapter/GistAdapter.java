package com.gist.nibinsalim.gistcomment.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gist.nibinsalim.gistcomment.R;
import com.gist.nibinsalim.gistcomment.retrofit.model.GistData;

import java.util.List;

/**
 * Created by nibinsalim on 13/05/18.
 * Description handles the gist data to be shown in recyclerview
 */

public class GistAdapter extends RecyclerView.Adapter<GistAdapter.ViewHolder> {

private int itemLayout;
        List<GistData> mGistList;
        Activity mContext;

public GistAdapter(int itemLayout,List<GistData> gistList, Activity context) {
        this.itemLayout = itemLayout;
        mGistList = gistList;
        mContext = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
        }

@Override
public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mFileName.setText("FileName: "+mGistList.get(position).getFilename());
        holder.mContent.setText("Content: "+ mGistList.get(position).getContent());
        }

@Override
public int getItemCount() {
        return mGistList.size();
        }

public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView mFileName, mContent;


    public ViewHolder(View itemView) {
        super(itemView);
        mFileName = (TextView) itemView.findViewById(R.id.tv_comments);
        mContent = (TextView) itemView.findViewById(R.id.tv_commentedBy);
    }
}
}
