package com.miguan.otk.module.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.AddressModel;
import com.miguan.otk.model.bean.Area;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2017/1/19. LiaoPeiKun Inc. All rights reserved.
 */

public class AreaFragmentPresenter extends BaseListActivityPresenter<AreaActivity, Area> {

    private StringBuilder mName = new StringBuilder();

    @Override
    protected void onCreate(AreaActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
    }

    @Override
    protected void onCreateView(AreaActivity view) {
        super.onCreateView(view);
        onRefresh();
        getAdapter().setOnItemClickListener(position -> {
            Area data = getAdapter().getItem(position);
            mName.append(data.getName() + " ");
            Intent intent = new Intent(getView(), AreaActivity.class);
            if (data.getCity() != null) {
                intent.putExtra("area_list", data.getCity());
                getView().setIntent(intent);
                onRefresh();
            } else if (data.getArea() != null) {
                intent.putExtra("area_list", data.getArea());
                getView().setIntent(intent);
                onRefresh();
            } else {
                getView().setResult(Activity.RESULT_OK, intent);
                EventBus.getDefault().post(mName);
                getView().finish();
            }
        });
    }

    @Override
    public void onRefresh() {
        List<Area> areaList = getView().getIntent().getParcelableArrayListExtra("area_list");
        if (areaList != null) {
            Observable.just(areaList).unsafeSubscribe(getRefreshSubscriber());
        } else {
            AddressModel.getInstance().getAreaList().unsafeSubscribe(getRefreshSubscriber());
        }
    }

}
