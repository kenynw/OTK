package com.miguan.otk.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.module.news.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/15. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainNewsPresenter.class)
public class MainNewsFragment extends ChainFragment<MainNewsPresenter> {

    @Bind(R.id.tab_main_news)
    TabLayout mTabLayout;

    @Bind(R.id.pager_main_news)
    ViewPager mPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_news, container, false);
        ButterKnife.bind(this, view);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 1; i < getResources().getStringArray(R.array.tab_news_list).length + 1; i++) {
            NewsListFragment fragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        mPager.setAdapter(new TitlePagerAdapter(getActivity(), R.array.tab_news_list, fragments, getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);

        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

}
