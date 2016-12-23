package com.miguan.otk.module.match;

import android.support.annotation.NonNull;

import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.miguan.otk.model.bean.User;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */

public class VSListPresenter extends BeamListFragmentPresenter<VSListFragment, User> {

    @Override
    protected void onCreateView(@NonNull VSListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<User> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            User user = new User();
            user.setAvatar("http://oss.otkpk.com/uploads/photo/20160922/oJZMvzELuoo3wDvxQ4qSvU5Dzqj6Mv1L.png");
            user.setName("otk");
            list.add(user);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
