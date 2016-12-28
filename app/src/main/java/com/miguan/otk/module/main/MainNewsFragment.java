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
import com.miguan.otk.widget.StickyNavLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/15. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainNewsPresenter.class)
public class MainNewsFragment extends BaseDataFragment<MainNewsPresenter, News> {

    @Bind(R.id.sticky_view_news)
    StickyNavLayout mNavLayout;

    @Bind(R.id.id_stickynavlayout_tab)
    TabLayout mTabLayout;

    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager mPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_news, container, false);
        ButterKnife.bind(this, view);

        List<Fragment> fragments = new ArrayList<>();
        for (int i=0; i<getResources().getStringArray(R.array.tab_news_list).length; i++) {
            NewsListFragment fragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        mPager.setAdapter(new TitlePagerAdapter(getActivity(), R.array.tab_news_list, fragments, getFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
        mNavLayout.setOnHeaderScrollListener((scrollY, isHidden) -> {
            for (int i=0; i<fragments.size(); i++) {
                NewsListFragment fragment = (NewsListFragment) fragments.get(i);
                if (fragment.getListView() != null) fragment.getListView().getSwipeToRefresh().setEnabled(scrollY == 0);
            }
        });

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
