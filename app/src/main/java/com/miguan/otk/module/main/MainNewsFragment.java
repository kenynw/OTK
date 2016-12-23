package com.miguan.otk.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.model.bean.News;
import com.miguan.otk.module.news.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/15. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainNewsPresenter.class)
public class MainNewsFragment extends BaseDataFragment<MainNewsPresenter, News> {

    @Bind(R.id.tab_main_news)
    TabLayout mTabLayout;

    @Bind(R.id.pager_main_news)
    ViewPager mPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_news, null);
        ButterKnife.bind(this, view);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewsListFragment());
        fragments.add(new NewsListFragment());

        mPager.setAdapter(new TitlePagerAdapter(getActivity(), R.array.tab_match_list, fragments, getFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);

        return view;
    }

    @Override
    public void setData(News news) {

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

}
