package com.miguan.otk.model.services;

import com.sgun.utils.LUtils;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class ServicesResponse<T> extends  Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e.getCause() instanceof ServiceException) {
            serviceError((ServiceException) e.getCause());
        } else {
            serviceError(new ServiceException(-1, "网络错误"));
        }
    }

    @Override
    public void onNext(T t) {

    }

    private void serviceError(ServiceException e) {
        if (e.getCode() == -2) {
            LUtils.getPreferences().edit().clear().apply();
        }
        LUtils.toast(e.getMsg());
    }

}
