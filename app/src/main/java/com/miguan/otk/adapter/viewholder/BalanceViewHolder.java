package com.miguan.otk.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Balance;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class BalanceViewHolder extends BaseViewHolder<Balance> {

    @Bind(R.id.tv_balance_title)
    TextView mTvTitle;

    @Bind(R.id.tv_balance_detail)
    TextView mTvDetail;

    @Bind(R.id.tv_balance_date)
    TextView mTvDate;

    @Bind(R.id.tv_balance_rest)
    TextView mTvRest;

    public BalanceViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_balance);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Balance balance) {
        mTvTitle.setText(balance.getFolw());
        mTvDate.setText(balance.getCreate_time());
        mTvDetail.setText(balance.getNum());
        mTvRest.setText(String.format(getContext().getString(R.string.label_balance), balance.getQty()));
    }
}
