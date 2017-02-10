package com.miguan.otk.module.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.dsk.chain.bijection.ChainAppCompatActivity;
import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.module.account.LoginActivity;
import com.miguan.otk.module.settings.SettingsActivity;
import com.sgun.utils.LUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class MainMinePresenter extends BaseDataFragmentPresenter<MainMineFragment, User> {

    @Override
    protected void onCreate(MainMineFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreateView(MainMineFragment view) {
        super.onCreateView(view);
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("token", ""))) {
            getView().isLogin(false);
        } else {
            setData();
        }
    }

    private void setData() {
        UserModel.getInstance().userInfo().subscribe(getSubscriber());
    }

    public void toActivity(Class<? extends ChainAppCompatActivity> clazz) {
        if (isLogin()) {
            Intent intent = new Intent(getView().getActivity(), clazz);
            intent.putExtra("user", getData());
            getView().startActivity(intent);
        }
    }

    public void toLogin() {
        Intent intent = new Intent(getView().getActivity(), LoginActivity.class);
        getView().startActivityForResult(intent, 1);
    }

    public void toSettings() {
        Intent intent = new Intent(getView().getActivity(), SettingsActivity.class);
        getView().startActivityForResult(intent, 2);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onLoginEvent(User user) {
        setData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAvatar(Uri uri) {
        getView().setAvatar(uri);
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("token", ""))) {
            toLogin();
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1 && data != null && data.getParcelableExtra("user") != null) {
                publishData(data.getParcelableExtra("user"));
            }
            if (requestCode == 2 && TextUtils.isEmpty(LUtils.getPreferences().getString("token", ""))) {
                LUtils.log("token" + LUtils.getPreferences().getString("token", ""));
                getView().isLogin(false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
