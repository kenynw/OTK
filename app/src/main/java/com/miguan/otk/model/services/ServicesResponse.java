package com.miguan.otk.model.services;

import android.content.Intent;

import com.miguan.otk.utils.LUtils;

import rx.Subscriber;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class ServicesResponse<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ServiceException) {
            serviceError((ServiceException) e);
        } else {
            serviceError(new ServiceException(-1, "网络错误"));
        }
    }

    @Override
    public void onNext(T t) {

    }

    private void serviceError(ServiceException e) {
        if (e.getCode() == 3) {
            LUtils.getPreferences().edit().clear().apply();
            Intent intent = new Intent();
            intent.setAction("com.miguan.otk:login");

        }
        LUtils.toast(e.getMsg());
    }

}
