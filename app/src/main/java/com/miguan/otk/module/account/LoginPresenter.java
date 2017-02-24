package com.miguan.otk.module.account;

import android.app.Activity;
import android.content.Intent;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.config.UserPreferences;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class LoginPresenter extends Presenter<LoginActivity> {

    @Override
    protected void onCreateView(LoginActivity view) {
        super.onCreateView(view);

    }

    public void login(String mobile, String password) {
        UserModel.getInstance().login(mobile, password).subscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                Intent intent = new Intent();
                intent.putExtra("user", user);
                getView().setResult(Activity.RESULT_OK, intent);
                getView().finish();
                EventBus.getDefault().post(user);
            }
        });
    }

    private void doLogin() {
        String account = UserPreferences.getUserID();
        String token = UserPreferences.getNIMToken();

        LUtils.log("account: " + account + ", token: " + token);

        NimUIKit.doLogin(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo info) {
                LUtils.log("登录成功 account: " + info.getAccount() + ", token: " + info.getToken());

                Intent intent = new Intent();
//                intent.putExtra("user", user);
                getView().setResult(Activity.RESULT_OK, intent);
                getView().finish();
//                EventBus.getDefault().post(user);
            }

            @Override
            public void onFailed(int code) {
                if (code == 302 || code == 404) {
                    LUtils.toast("账号或密码错误");
                } else {
                    LUtils.toast("登录失败: " + code);
                }
            }

            @Override
            public void onException(Throwable throwable) {
                LUtils.toast("无效输入");
            }
        });
    }

    public void doOauthVerify(SHARE_MEDIA media) {
        UMShareAPI.get(getView()).doOauthVerify(getView(), media, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        UMShareAPI.get(getView()).onActivityResult(requestCode, resultCode, data);
    }

}
