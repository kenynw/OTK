package com.miguan.otk.module.battle;

import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2017/2/6. LiaoPeiKun Inc. All rights reserved.
 */

public class ShotBrowsePresenter extends Presenter<ShotBrowseActivity> {

    private int mBattleID;

    private String mKind;

    private String mUri;

    @Override
    protected void onCreate(ShotBrowseActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mBattleID = getView().getIntent().getIntExtra("battle_id", 0);
        mKind = getView().getIntent().getStringExtra("kind");
        mUri = getView().getIntent().getStringExtra("uri");

        LUtils.log("id: " + mBattleID + ", kind: " + mKind);
    }

    public void upload() {
//        ImageModel.getInstance().uploadImageAsync(new File(Uri.parse(mUri).getPath()))
//                .unsafeSubscribe(new ServicesResponse<String>() {
//                    @Override
//                    public void onNext(String s) {
//                        super.onNext(s);
//                        LUtils.log(s);
//                    }
//                });

        BattleModel.getInstance().upload(Uri.parse(mUri), mBattleID, mKind)
                .unsafeSubscribe(new ServicesResponse<Battle>() {
                    @Override
                    public void onNext(Battle battle) {

                    }
                });
    }

}
