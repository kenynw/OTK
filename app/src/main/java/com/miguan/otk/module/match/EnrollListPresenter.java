package com.miguan.otk.module.match;

import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */

class EnrollListPresenter extends BaseListActivityPresenter<EnrollListActivity, User> {

    private int mType;

    @Override
    protected void onCreate(EnrollListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mType = getView().getIntent().getIntExtra("type", 0);
    }

    @Override
    protected void onCreateView(EnrollListActivity view) {
        super.onCreateView(view);
        if (mType == 0) getView().setToolbarTitle(R.string.title_activity_enroll);
        else getView().setToolbarTitle(R.string.title_activity_bench);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<User> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            User user = new User();
            user.setUsername("神代冬琉");
            user.setPhoto("http://oss.otkpk.com/images/default-toux.jpg");
            list.add(user);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
