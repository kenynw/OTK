package com.miguan.otk.module.main;

import android.support.v4.widget.SwipeRefreshLayout;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Home;

/**
 *  2016. LiaoPeiKun Inc. All rights reserved.
 */
class MainMatchPresenter extends BaseDataFragmentPresenter<MainMatchFragment, Home> implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreateView(MainMatchFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getHome().unsafeSubscribe(getSubscriber());
    }
}
