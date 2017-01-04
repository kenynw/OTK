package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.adapter.MatchScheduleAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(SchedulePresenter.class)
public class ScheduleFragment extends ChainFragment<SchedulePresenter> {

    @Bind(R.id.pager_match_schedule)
    ViewPager mPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment_schedule, container, false);
        ButterKnife.bind(this, view);

        mPager.setAdapter(new MatchScheduleAdapter(getChildFragmentManager()));

        return view;
    }

}
