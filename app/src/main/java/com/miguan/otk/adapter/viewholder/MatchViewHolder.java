package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.module.match.MatchDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchViewHolder extends BaseViewHolder<Match> {

    @Bind(R.id.tv_match_id)
    TextView mTvID;

    @Bind(R.id.tv_match_nature)
    TextView mTvState;

    @Bind(R.id.dv_match_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_match_title)
    TextView mTvTitle;

    @Bind(R.id.tv_match_time)
    TextView mTvTime;

    public MatchViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_match);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Match data) {
        mTvID.setText(String.format(getContext().getString(R.string.label_match_id), data.getMatch_id()));
        mTvState.setText(data.getState() + "");
        mDvThumb.setImageURI(Uri.parse(data.getEnrolled()));
        mTvTitle.setText(data.getTitle());
        mTvTime.setText(data.getTime());
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MatchDetailActivity.class);
            i.putExtra("match_id", data.getMatch_id());
            getContext().startActivity(i);
        });
    }
}