package com.miguan.otk.module.match;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.adapter.viewholder.MatchRecordViewHolder;
import com.miguan.otk.model.bean.Match;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MatchRecordPresenter.class)
public class MatchRecordFragment extends BaseListFragment<MatchRecordPresenter, Match> {

    @Override
    public BaseViewHolder<Match> createViewHolder(ViewGroup parent, int viewType) {
        return new MatchRecordViewHolder(parent);
    }

}