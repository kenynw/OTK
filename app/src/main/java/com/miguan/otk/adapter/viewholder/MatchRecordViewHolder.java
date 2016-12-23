package com.miguan.otk.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchRecordViewHolder extends BaseViewHolder<Match> {

    @Bind(R.id.tv_match_id)
    TextView mTvID;

    @Bind(R.id.tv_match_nature)
    TextView mTvNature;

    @Bind(R.id.tv_match_title)
    TextView mTvTitle;

    @Bind(R.id.tv_match_user_1)
    TextView mTvUser1;

    @Bind(R.id.tv_match_user_2)
    TextView mTvUser2;

    public MatchRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_match_record);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Match data) {
        mTvID.setText(String.format(getContext().getString(R.string.label_match_id), data.getMatch_id()));
        mTvNature.setText(data.getNature());
        mTvTitle.setText(data.getTitle());
    }
}
