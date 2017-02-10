package com.miguan.otk.module.user;

import android.support.v7.app.AlertDialog;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.services.ServicesResponse;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class SignPresenter extends BaseDataActivityPresenter<SignActivity, Sign> {

    @Override
    protected void onCreateView(SignActivity view) {
        super.onCreateView(view);
        UserModel.getInstance().getSignInfo(0, 2017).unsafeSubscribe(new ServicesResponse<Sign>() {
            @Override
            public void onNext(Sign sign) {
                getView().setData(sign);
            }
        });
    }

    public void sign() {
        UserModel.getInstance().sign().unsafeSubscribe(new ServicesResponse<Sign>() {
            @Override
            public void onNext(Sign sign) {
                String result = sign.getBaoxiangscore() != 0 ?
                        String.format(getView().getString(R.string.text_sign_series_bonus), sign.getBaoxiangscore()) : "";

                new AlertDialog.Builder(getView())
                        .setMessage(result + String.format(getView().getString(R.string.text_sign_bonus), sign.getCurrency()))
                        .setPositiveButton(R.string.btn_ok, null)
                        .show();

                getView().setBtnDisable();
            }
        });
    }

}
