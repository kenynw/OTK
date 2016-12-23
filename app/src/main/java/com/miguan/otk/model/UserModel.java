package com.miguan.otk.model;


import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserModel {

    private static UserModel sInstance = null;

    public static UserModel getInstance() {
        if (null == sInstance) {
            synchronized (UserModel.class) {
                if (null == sInstance) sInstance = new UserModel();
            }
        }
        return sInstance;
    }

    /**
     * 发送验证码
     * @param mobile 手机号
     * @return 发送结果
     */
    public Observable<Boolean> sendCaptcha(String mobile) {
        return ServicesClient.getServices().sendCaptcha(mobile).compose(new DefaultTransform<>());
    }

}
