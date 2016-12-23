package com.miguan.otk.module.rank;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.User;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/11/24. LiaoPeiKun Inc. All rights reserved.
 */
class RankListPresenter extends BaseListFragmentPresenter<RankListFragment, User> {

    @Override
    protected void onCreateView(RankListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<User> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            User user = new User();
            user.setAvatar("http://www.otkpk.com/images/default-toux.jpg");
            user.setName("DSK");
            user.setPoints("300");
            list.add(user);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }

}
