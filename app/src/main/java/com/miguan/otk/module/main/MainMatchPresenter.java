package com.miguan.otk.module.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.bean.Match;

import java.util.ArrayList;
import java.util.List;

/**
 *  2016. LiaoPeiKun Inc. All rights reserved.
 */
class MainMatchPresenter extends BaseDataFragmentPresenter<MainMatchFragment, Match> implements TabLayout.OnTabSelectedListener {

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        FragmentManager fm = getView().getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        MatchListFragment fragment;
//        if (mFragments.size() > tab.getPosition() && mFragments.get(tab.getPosition()) != null) {
//            fragment = (MatchListFragment) mFragments.get(tab.getPosition());
//        } else {
//            fragment = MatchListPresenter.getFragment(tab.getPosition());
//            mFragments.add(tab.getPosition(), fragment);
//        }
//        ft.replace(R.id.container_match_list, fragment);
//        ft.commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (mFragments.get(tab.getPosition()) != null) {
            FragmentTransaction ft = getView().getFragmentManager().beginTransaction();
            ft.hide(mFragments.get(tab.getPosition()));
            ft.commit();
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
