package com.miguan.otk.model;


import android.content.Context;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.sgun.utils.LUtils;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserModel extends AbsModel {

    private static UserModel sInstance = null;

    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    /**
     * 发送验证码
     * @param mobile 手机号
     * @return 发送结果
     */
    public Observable<Boolean> sendCaptcha(String mobile) {
        return ServicesClient.getServices().sendCaptcha(mobile).compose(new DefaultTransform<>());
    }

    public Observable<User> login(String mobile, String password) {
        return ServicesClient.getServices().login(mobile, password).compose(new DefaultTransform<>());
    }

    @Override
    protected void onAppCreateOnBackThread(Context context) {
        super.onAppCreateOnBackThread(context);
        LUtils.log("onAppCreateOnBackThread");
    }

}
