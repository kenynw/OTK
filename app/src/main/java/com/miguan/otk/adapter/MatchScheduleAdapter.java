package com.miguan.otk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miguan.otk.module.match.AgainstListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */
public class MatchScheduleAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public MatchScheduleAdapter(FragmentManager fm) {
        super(fm);
        mList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            AgainstListFragment fragment = new AgainstListFragment();
            mList.add(fragment);
        }

    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

}
