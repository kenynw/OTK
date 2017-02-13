package com.miguan.otk.module.user;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.widget.EventDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
                        if (sign.getCause() != null && sign.getCause().size() > 0) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
                            List<CalendarDay> list = new ArrayList<>();
                            for (Sign.Cause cause : sign.getCause()) {
                                try {
                                    list.add(CalendarDay.from(format.parse(cause.getSign_in_date())));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                widget.addDecorator(new EventDecorator(Color.RED, list));
                            }
                        }
                    }
                });
    }

}
