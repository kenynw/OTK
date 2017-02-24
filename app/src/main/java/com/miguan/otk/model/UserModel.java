package com.miguan.otk.model;


import com.dsk.chain.model.AbsModel;
import com.miguan.otk.config.UserPreferences;
import com.miguan.otk.model.bean.Balance;
import com.miguan.otk.model.bean.Feedback;
import com.miguan.otk.model.bean.Game;
import com.miguan.otk.model.bean.Message;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.bean.Withdraw;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return ServicesClient.getServices().sendCaptchaUpdate(mobile, UserPreferences.getToken()).compose(new DefaultTransform<>());
    }

    public Observable<User> login(String mobile, String password) {
        return ServicesClient.getServices().login(mobile, password)
                .doOnNext(this::saveAccount)
//                .flatMap(new Func1<User, Observable<User>>() {
//                    @Override
//                    public Observable<User> call(User user) {
//                        return Observable.create(subscriber -> {
//                            String account = UserPreferences.getUserID();
//                            String token = UserPreferences.getNIMToken();
//
//                            LUtils.log("account: " + account + ", token: " + token);
//
//                            NimUIKit.doLogin(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
//                                @Override
//                                public void onSuccess(LoginInfo info) {
//                                    LUtils.log("登录成功 account: " + info.getAccount() + ", token: " + info.getToken());
//                                    subscriber.onNext(user);
//                                }
//
//                                @Override
//                                public void onFailed(int code) {
//                                    if (code == 302 || code == 404) {
//                                        LUtils.toast("账号或密码错误");
//                                        subscriber.onError(new ServiceException(code, "账号或密码错误"));
//                                    } else {
//                                        LUtils.toast("登录失败: " + code);
//                                        subscriber.onError(new ServiceException(code, "登录失败: " + code));
//                                    }
//                                }
//
//                                @Override
//                                public void onException(Throwable throwable) {
//                                    LUtils.toast("无效输入");
//                                    subscriber.onError(new ServiceException(0, "无效输入"));
//                                }
//                            });
//                        });
//                    }
//                })
                .compose(new DefaultTransform<>());
    }

    public Observable<User> register(String username, String mobile, String code, String password) {
        return ServicesClient.getServices().register(username, mobile, code, password)
                .doOnNext(this::saveAccount)
                .compose(new DefaultTransform<>());
    }

    public Observable<User> userInfo() {
        return ServicesClient.getServices().userInfo(UserPreferences.getToken())
                .compose(new DefaultTransform<>());
    }

    public Observable<Boolean> modifyPwd(String mobile, String code, String newPwd) {
        return ServicesClient.getServices().modifyPwd(mobile, code, newPwd).compose(new DefaultTransform<>());
    }

    public Observable<User> getProfile() {
        return ServicesClient.getServices().userProfile(UserPreferences.getToken()).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> setProfile(Map<String, String> map) {
        map.put("token", UserPreferences.getToken());
        return ServicesClient.getServices().modifyProfile(map).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> setProfile(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("token", UserPreferences.getToken());
        map.put(key, value);
        return ServicesClient.getServices().modifyProfile(map).compose(new DefaultTransform<>());
    }

    /**
     * 签到信息
     * @return
     */
    public Observable<Sign> getSignInfo(String month, Integer year) {
        return ServicesClient.getServices().signDetail(UserPreferences.getToken(), month, year).compose(new DefaultTransform<>());
    }

    /**
     * 签到
     * @return
     */
    public Observable<Sign> sign() {
        return ServicesClient.getServices().sign(UserPreferences.getToken()).compose(new DefaultTransform<>());
    }

    public Observable<List<Balance>> getBalanceList(String type, Integer page) {
        return ServicesClient.getServices().balanceList(type, UserPreferences.getToken(), page)
                .compose(new DefaultTransform<>());
    }

    /**
     * 提现记录
     */
    public Observable<Withdraw> getWithdrawList() {
        return ServicesClient.getServices().withdrawRecord(UserPreferences.getToken())
                .compose(new DefaultTransform<>());
    }

    /**
     * 提现记录
     */
    public Observable<List<Withdraw>> getWithdrawList(Integer page) {
        return ServicesClient.getServices().withdrawRecord(UserPreferences.getToken(), page)
                .compose(new DefaultTransform<>());
    }

    /**
     * 提现
     */
    public Observable<Boolean> withdraw(Integer money) {
        return ServicesClient.getServices().withdraw(UserPreferences.getToken(), money).compose(new DefaultTransform<>());
    }

    /**
     * 绑定支付宝
     */
    public Observable<Boolean> bindAlipay(String mobile, String account, String captcha) {
        return ServicesClient.getServices().bindAlipay(UserPreferences.getToken(), mobile, account, captcha).compose(new DefaultTransform<>());
    }

    /**
     * 游戏帐号
     */
    public Observable<List<Game>> getGameAccounts() {
        return ServicesClient.getServices().gameAccounts(UserPreferences.getToken()).compose(new DefaultTransform<>());
    }

    /**
     * 添加游戏帐号
     */
    public Observable<Boolean> addGameAccount(String name, String account) {
        return ServicesClient.getServices().addGameAccount(UserPreferences.getToken(), name, account).compose(new DefaultTransform<>());
    }

    /**
     * 编辑游戏帐号
     */
    public Observable<Boolean> updateGameAccount(int id, String account) {
        return ServicesClient.getServices().updateGameAccount(UserPreferences.getToken(), id, account).compose(new DefaultTransform<>());
    }

    /**
     * 消息分类最新一条
     */
    public Observable<List<Message>> getMessageDesc() {
        return ServicesClient.getServices().getMessageDesc(UserPreferences.getToken()).compose(new DefaultTransform<>());
    }

    /**
     * 消息列表
     */
    public Observable<List<Message>> getMessageList(Integer type, Integer page) {
        return ServicesClient.getServices().getMessageList(UserPreferences.getToken(), type, page).compose(new DefaultTransform<>());
    }

    /**
     * 吐槽一下
     * @param type
     * @return
     */
    public Observable<Feedback> saveFeedback(Integer type, String contact, String content, String img) {
        return ServicesClient.getServices()
                .feedback(UserPreferences.getToken(), type, contact, content, img, 0)
                .compose(new DefaultTransform<>());
    }

    private void saveAccount(User user) {
        UserPreferences.setUserID(user.getUser_id() + "");
        UserPreferences.setNIMToken(user.getAuth_key());
        UserPreferences.setToken(user.getToken());
    }

}
