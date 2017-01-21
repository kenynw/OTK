package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.ScheduleViewHolder;
import com.miguan.otk.model.bean.Battle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(ScheduleListPresenter.class)
public class ScheduleListFragment extends BaseListFragment<ScheduleListPresenter, Battle> {

    @Bind(R.id.tv_vs_round)
    TextView mTvRound;

    @Bind(R.id.btn_vs_left)
    Button mBtnLeft;

    @Bind(R.id.btn_vs_right)
    Button mBtnRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);

        mBtnLeft.setOnClickListener(v -> getPresenter().changeRound(-1));
        mBtnRight.setOnClickListener(v -> getPresenter().changeRound(1));

        return view;
    }

    public void setData(Battle schedule) {
        mTvRound.setText(String.format(getString(R.string.text_which_rounds), schedule.getRound_count()));
    }

    @Override
    public BaseViewHolder<Battle> createViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleViewHolder(parent);
    }

    @Override
    public int getLayout() {
        return R.layout.match_fragment_schedule_list;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setLoadMoreAble(false).setRefreshAble(false).setContainerEmptyRes(R.layout.empty_against_list);
    }

}
