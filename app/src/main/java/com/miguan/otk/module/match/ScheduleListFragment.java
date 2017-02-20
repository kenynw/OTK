package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.ScheduleViewHolder;
import com.miguan.otk.model.bean.Schedule;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(ScheduleListPresenter.class)
public class ScheduleListFragment extends BaseListFragment<ScheduleListPresenter, Schedule> {

    @Bind(R.id.tv_schedule_round)
    TextView mTvRound;

    @Bind(R.id.iv_schedule_left)
    ImageView mIvLeft;

    @Bind(R.id.iv_schedule_right)
    ImageView mIvRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    public void setData(Schedule schedule) {
        mTvRound.setText(String.format(getString(R.string.text_which_rounds), schedule.getRound() + 1));
        if (schedule.getRound() >= schedule.getRound_count()) {
            mIvRight.setVisibility(View.INVISIBLE);
        } else {
            mIvRight.setVisibility(View.VISIBLE);
            mIvRight.setOnClickListener(v -> getPresenter().changeRound(1));
        }
        if (schedule.getRound() == 0) {
            mIvLeft.setVisibility(View.INVISIBLE);
        } else {
            mIvLeft.setVisibility(View.VISIBLE);
            mIvLeft.setOnClickListener(v -> getPresenter().changeRound(-1));
        }
    }

    @Override
    public BaseViewHolder<Schedule> createViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleViewHolder(parent);
    }

    @Override
    public int getLayout() {
        return R.layout.match_fragment_schedule_list;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig()
                .setContainerEmptyRes(R.layout.empty_against_list)
                .hasItemDecoration(false)
                .setLoadMoreAble(false)
                .setRefreshAble(false)
                .setNoMoreAble(false);
    }

}
