package com.miguan.otk.module.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.bean.Address;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */

public class AddressListPresenter extends BaseListActivityPresenter<AddressListActivity, Address> {

    /**
     * 选择模式，在订单确认页面中
     */
    public static final int TYPE_CHOICE = 0x100;

    /**
     * 列表浏览模式
     */
    public static final int TYPE_LIST = 0x200;

    private int mType;

    @Override
    protected void onCreate(AddressListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mType = getView().getIntent().getIntExtra("type", 0);
        getAdapter().setOnItemClickListener(position -> {
            Intent intent = new Intent();
            intent.putExtra("address", getAdapter().getItem(position));
            getView().setResult(Activity.RESULT_OK, intent);
            getView().finish();
        });
    }

    @Override
    protected void onCreateView(AddressListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Address> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Address address = new Address();
            address.setTrue_name("廖培坤");
            address.setCity("福建省  厦门市");
            address.setMob_phone("13215698921");
            address.setAddress("思明区望海路16号之一301米冠网络");
            list.add(address);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
