package com.miguan.otk.module.user;

import android.app.Activity;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Game;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;


/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class GameAccountEditPresenter extends Presenter<GameAccountEditActivity> {

    private Game mGame;

    @Override
    protected void onCreate(GameAccountEditActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mGame = getView().getIntent().getParcelableExtra("game");
    }

    public void save(String name) {
        UserModel.getInstance().updateGameAccount(mGame.getId(), name).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean result) {
                if (result) {
                    LUtils.toast("更新成功");
                    getView().setResult(Activity.RESULT_OK);
                    getView().finish();
                }
            }
        });
    }

}
