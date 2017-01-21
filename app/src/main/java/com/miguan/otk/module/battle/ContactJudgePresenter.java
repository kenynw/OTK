package com.miguan.otk.module.battle;

import android.content.Intent;
import android.net.Uri;

import com.dsk.chain.bijection.Presenter;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2017/1/6. LiaoPeiKun Inc. All rights reserved.
 */

class ContactJudgePresenter extends Presenter<ContactJudgeActivity> {

    void startQQ() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=1655647371"));
        try {
            getView().startActivity(intent);
        } catch (Exception e) {
            LUtils.toastLong("未安装手Q或安装的版本不支持");
        }
    }

    void joinQQGroup() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + "YTT9mPgKCC-LR_48OUvlQWF3dBpRSJ0j"));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            getView().startActivity(intent);
        } catch (Exception e) {
            LUtils.toastLong("未安装手Q或安装的版本不支持");
        }
    }

}
