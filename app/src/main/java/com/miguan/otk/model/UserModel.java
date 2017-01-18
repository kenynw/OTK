package com.miguan.otk.model;


import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.Balance;
import com.miguan.otk.model.bean.Feedback;
import com.miguan.otk.model.bean.Game;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.bean.Withdraw;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.sgun.utils.LUtils;

import java.util.List;

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

    /**
     * 忘记密码验证码
     * @param mobile 手机号
     * @return 发送结果
     */
    public Observable<Boolean> updateCaptcha(String mobile) {
        return ServicesClient.getServices().sendCaptchaUpdate(mobile, LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
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

    public Observable<Boolean> setProfile(String photo, String qq, String email, String actuality, String birthday, String province, String city, String sign) {
        return ServicesClient.getServices().modifyProfile(LUtils.getPreferences().getString("token", ""),
                photo, qq, email, actuality, birthday, province, city, sign
        ).compose(new DefaultTransform<>());
    }

    /**
     * 签到信息
     * @return
     */
    public Observable<Sign> getSignInfo() {
        return ServicesClient.getServices().signDetail(LUtils.getPreferences().getString("token", ""), 1, 2017).compose(new DefaultTransform<>());
    }

    /**
     * 签到
     * @return
     */
    public Observable<Boolean> sign() {
        return ServicesClient.getServices().sign(LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
    }

    public Observable<List<Balance>> getBalanceList(String type, Integer page) {
        return ServicesClient.getServices().balanceList(type, LUtils.getPreferences().getString("token", ""), page)
                .compose(new DefaultTransform<>());
    }

    /**
     * 提现记录
     */
    public Observable<Withdraw> getWithdrawList() {
        return ServicesClient.getServices().withdrawRecord(LUtils.getPreferences().getString("token", ""))
                .compose(new DefaultTransform<>());
    }

    /**
     * 提现记录
     */
    public Observable<List<Withdraw>> getWithdrawList(Integer page) {
        return ServicesClient.getServices().withdrawRecord(LUtils.getPreferences().getString("token", ""), page)
                .compose(new DefaultTransform<>());
    }

    /**
     * 提现
     */
    public Observable<Boolean> withdraw(Integer money) {
        return ServicesClient.getServices().withdraw(LUtils.getPreferences().getString("token", ""), money).compose(new DefaultTransform<>());
    }

    /**
     * 绑定支付宝
     */
    public Observable<Boolean> bindAlipay(String mobile, String account, String captcha) {
        return ServicesClient.getServices().bindAlipay(LUtils.getPreferences().getString("token", ""), mobile, account, captcha).compose(new DefaultTransform<>());
    }

    /**
     * 游戏帐号
     */
    public Observable<List<Game>> getGameAccounts() {
        return ServicesClient.getServices().gameAccounts(LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
    }

    /**
     * 添加游戏帐号
     */
    public Observable<Boolean> addGameAccount(String name, String account) {
        return ServicesClient.getServices().addGameAccount(LUtils.getPreferences().getString("token", ""), name, account).compose(new DefaultTransform<>());
    }

    /**
     * 编辑游戏帐号
     */
    public Observable<Boolean> updateGameAccount(int id, String account) {
        return ServicesClient.getServices().updateGameAccount(LUtils.getPreferences().getString("token", ""), id, account).compose(new DefaultTransform<>());
    }

    /**
     * 吐槽一下
     * @param type
     * @return
     */
    public Observable<Feedback> saveFeedback(Integer type, String contact, String content, String img) {
        return ServicesClient.getServices().feedback(
                LUtils.getPreferences().getString("token", ""), type, contact, content, img, 0)
                .compose(new DefaultTransform<>());
    }

}
