package com.miguan.otk.module.account;

import android.app.Activity;
import android.content.Intent;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

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
                LUtils.getPreferences().edit().putString("token", user.getToken()).apply();
                Intent intent = new Intent();
                intent.putExtra("user", user);
                getView().setResult(Activity.RESULT_OK, intent);
                getView().finish();
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
