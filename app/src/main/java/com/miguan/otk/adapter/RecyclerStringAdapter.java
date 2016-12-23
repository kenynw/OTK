package com.miguan.otk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguan.otk.R;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class RecyclerStringAdapter extends RecyclerView.Adapter<RecyclerStringAdapter.SpecViewHolder> {

    private Context mContext;

    private String[] mList;

    public RecyclerStringAdapter(Context context, String[] list) {
        mContext = context;
        mList = list;
    }

    @Override
    public SpecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new Button(mContext);
        textView.setBackgroundResource(R.drawable.stroke_gray_shape);
        return new SpecViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(SpecViewHolder holder, int position) {
        holder.mText.setText(mList[position]);
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    class SpecViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        SpecViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView;
        }
    }

}
