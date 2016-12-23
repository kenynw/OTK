package com.miguan.otk.module.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.adapter.MainFragmentAdapter;

/**
 * Created by LPK on 2016/11/22.
 */
class MainPresenter extends Presenter<MainActivity> implements TabLayout.OnTabSelectedListener {

    private MainFragmentAdapter mAdapter;

    @Override
    protected void onCreate(MainActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mAdapter = new MainFragmentAdapter(getView().getSupportFragmentManager());
    }

    public MainFragmentAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = (Fragment) mAdapter.instantiateItem(getView().getContainer(), tab.getPosition());
        mAdapter.setPrimaryItem(getView().getContainer(), tab.getPosition(), fragment);
        mAdapter.finishUpdate(getView().getContainer());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
