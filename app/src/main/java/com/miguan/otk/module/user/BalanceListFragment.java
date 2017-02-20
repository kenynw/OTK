package com.miguan.otk.module.user;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.miguan.otk.adapter.viewholder.BalanceViewHolder;
import com.miguan.otk.model.bean.Balance;

import static com.miguan.otk.module.user.BalanceListPresenter.BALANCE_SCORE;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(BalanceListPresenter.class)
public class BalanceListFragment extends BaseListFragment<BalanceListPresenter, Balance> {

    @Override
    public BalanceViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new BalanceViewHolder(parent, getArguments().getString("type", BALANCE_SCORE));
    }

}
