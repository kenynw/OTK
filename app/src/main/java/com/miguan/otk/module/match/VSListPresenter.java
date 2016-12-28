package com.miguan.otk.module.match;

import android.support.annotation.NonNull;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.VS;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */

public class VSListPresenter extends BaseListFragmentPresenter<VSListFragment, VS> {

    @Override
    protected void onCreateView(@NonNull VSListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<VS> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            VS vs = new VS();
            vs.setPlayer_a("名字长一点的黄");
            vs.setPlayer_b("机智的黄图哥");
            list.add(vs);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
