package com.miguan.otk.adapter.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Address;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */

public class AddressViewHolder extends BaseViewHolder<Address> {

    @Bind(R.id.tv_address_consignee)
    TextView mTvConsignee;

    @Bind(R.id.tv_address_mobile)
    TextView mTvMobile;

    @Bind(R.id.tv_address_city)
    TextView mTvCity;

    @Bind(R.id.tv_address_info)
    TextView mTvInfo;

    public AddressViewHolder(ViewGroup parent) {
        this(parent, R.layout.item_list_address);
    }

    public AddressViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Address data) {
        mTvConsignee.setText(data.getTrue_name());
        mTvMobile.setText(data.getMob_phone());
        mTvCity.setText(data.getCity());
        mTvInfo.setText(data.getAddress());
    }
}
