package com.miguan.otk.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsk.chain.bijection.BeamFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.module.rank.RankListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/11/24. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainRankPresenter.class)
public class MainRankFragment extends BeamFragment<MainRankPresenter> {

    String[] TITLES = new String[] {"积分", "奖金"};

    @Bind(R.id.tab_rank)
    TabLayout mTabLayout;

    @Bind(R.id.pager_rank)
    ViewPager mPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_rank, null);
        ButterKnife.bind(this, view);

        List<Fragment> list = new ArrayList<>();
        list.add(new RankListFragment());
        list.add(new RankListFragment());
        mPager.setAdapter(new TitlePagerAdapter(getActivity(), TITLES, list, getFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);

        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

}
