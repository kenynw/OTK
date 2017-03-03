package com.miguan.otk.adapter.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Withdraw;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/16. LiaoPeiKun Inc. All rights reserved.
 */

public class WithdrawViewHolder extends BaseViewHolder<Withdraw> {

    @Bind(R.id.tv_withdraw_money)
    TextView mTvMoney;

    @Bind(R.id.tv_withdraw_status)
    TextView mTvStatus;

    @Bind(R.id.tv_withdraw_fee)
    TextView mTvFee;

    @Bind(R.id.tv_withdraw_time_fee)
    TextView mTvTime;

    public WithdrawViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_withdraw);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Withdraw data) {
        mTvMoney.setText(data.getMoney() + "");

        mTvStatus.setText(data.getStatus());
        if ("成功".equals(data.getStatus())) {
            mTvStatus.setTextColor(getContext().getResources().getColor(R.color.green));
        } else if ("失败".equals(data.getStatus())) {
            mTvStatus.setTextColor(getContext().getResources().getColor(R.color.textSecondary));
        } else {
            mTvStatus.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
        }

        mTvTime.setText(data.getTime());
        if (data.getMoney() > 15) {
            mTvFee.setVisibility(View.VISIBLE);
        } else {
            mTvFee.setVisibility(View.GONE);
        }
    }

}
