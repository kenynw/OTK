package com.miguan.otk.module.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.adapter.MainFragmentAdapter;
import com.miguan.otk.model.CommonModel;
import com.miguan.otk.model.bean.Splash;

/**
 * Created by LPK on 2016/11/22.
 */
class MainPresenter extends BaseDataActivityPresenter<MainActivity, Splash> implements TabLayout.OnTabSelectedListener {

    private MainFragmentAdapter mAdapter;

    @Override
    protected void onCreate(MainActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mAdapter = new MainFragmentAdapter(getView().getSupportFragmentManager());
    }

    @Override
    protected void onCreateView(MainActivity view) {
        super.onCreateView(view);
        CommonModel.getInstance().getSplashImage("read-opening", null).unsafeSubscribe(getDataSubscriber());
        CommonModel.getInstance().update(getView());
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
