package com.miguan.otk.model;


import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.sgun.utils.LUtils;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserModel extends AbsModel {

    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    /**
     * 注册验证码
     * @param mobile 手机号
     * @return 发送结果
     */
    public Observable<Boolean> registerCaptcha(String mobile) {
        return ServicesClient.getServices().sendCaptchaRegister(mobile).compose(new DefaultTransform<>());
    }

    /**
     * 忘记密码验证码
     * @param mobile 手机号
     * @return 发送结果
     */
    public Observable<Boolean> forgotCaptcha(String mobile) {
        return ServicesClient.getServices().sendCaptchaReset(mobile).compose(new DefaultTransform<>());
    }

    public Observable<User> login(String mobile, String password) {
        return ServicesClient.getServices().login(mobile, password).compose(new DefaultTransform<>());
    }

    public Observable<User> register(String username, String mobile, String code, String password) {
        return ServicesClient.getServices().register(username, mobile, code, password).compose(new DefaultTransform<>());
    }

    public Observable<User> userInfo() {
        return ServicesClient.getServices().userInfo(LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> modifyPwd(String mobile, String code, String newPwd) {
        return ServicesClient.getServices().modifyPwd(mobile, code, newPwd).compose(new DefaultTransform<>());
    }

    public Observable<User> getProfile() {
        return ServicesClient.getServices().userProfile(LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
    }

    public Observable<User> setProfile(String qq, String email, String actuality, String birthday, String province, String city, String sign) {
        return ServicesClient.getServices().modifyProfile(
                LUtils.getPreferences().getString("token", ""),
                qq, email, actuality, birthday, province, city, sign
        ).compose(new DefaultTransform<>());
    }

    /**
     * 签到信息
     * @return
     */
    public Observable<Sign> getSignDetail() {
        return ServicesClient.getServices().signDetail(LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
    }

    /**
     * 签到
     * @return
     */
    public Observable<Boolean> sign() {
        return ServicesClient.getServices().sign(LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
    }

}
