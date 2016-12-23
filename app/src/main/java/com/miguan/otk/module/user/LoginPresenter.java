package com.miguan.otk.module.user;

import android.content.Intent;

import com.dsk.chain.bijection.Presenter;
import com.sgun.utils.LUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
class LoginPresenter extends Presenter<LoginActivity> {

    void login(String mobile, String password) {
        LUtils.toast("登录成功");
    }

    void doOauthVerify(SHARE_MEDIA media) {
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
