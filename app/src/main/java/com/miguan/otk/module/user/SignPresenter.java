package com.miguan.otk.module.user;

import android.support.v7.app.AlertDialog;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.services.ServicesResponse;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class SignPresenter extends BaseDataActivityPresenter<SignActivity, Sign> implements OnMonthChangedListener {

    @Override
    protected void onCreateView(SignActivity view) {
        super.onCreateView(view);
        UserModel.getInstance().getSignInfo("", 0).unsafeSubscribe(new ServicesResponse<Sign>() {
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
                        .setView(R.layout.dialog_sign_success)
                        .setMessage(result + String.format(getView().getString(R.string.text_sign_bonus), sign.getCurrency()))
                        .setPositiveButton(R.string.btn_ok, null)
                        .show();

                getView().setBtnDisable();
            }
        });
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        UserModel.getInstance().getSignInfo(String.format("%02d", date.getMonth() + 1), date.getYear())
                .unsafeSubscribe(new ServicesResponse<Sign>() {
                    @Override
                    public void onNext(Sign sign) {
                        getView().setCalendar(sign);
                    }
                });
    }

}
