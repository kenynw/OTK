package com.miguan.otk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miguan.otk.module.match.MatchInfoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchDetailAdapter extends FragmentPagerAdapter {

    private static final String[] TITLES = new String[] {"赛事信息", "赛事规则", "对阵表", "聊天室"};

    private List<Fragment> mFragments;

    public MatchDetailAdapter(FragmentManager fm) {
        super(fm);

        mFragments = new ArrayList<>();
        mFragments.add(new MatchInfoFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

}
