package com.miguan.otk.module.battle;

import android.content.Intent;
import android.net.Uri;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.constant.QQAction;
import com.miguan.otk.utils.LUtils;


/**
 * Copyright (c) 2017/1/6. LiaoPeiKun Inc. All rights reserved.
 */

public class ContactJudgePresenter extends Presenter<ContactJudgeActivity> {

    public void startQQ() {
        Intent intent = new Intent();
        intent.setData(Uri.parse(QQAction.Service));
        try {
            getView().startActivity(intent);
        } catch (Exception e) {
            LUtils.toast("未安装手Q或安装的版本不支持");
        }
    }

    public void joinQQGroup() {
        Intent intent = new Intent();
        intent.setData(Uri.parse(QQAction.Group));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            getView().startActivity(intent);
        } catch (Exception e) {
            LUtils.toast("未安装手Q或安装的版本不支持");
        }
    }

}
