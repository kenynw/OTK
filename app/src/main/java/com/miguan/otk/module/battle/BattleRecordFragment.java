package com.miguan.otk.module.battle;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.adapter.viewholder.BattleRecordViewHolder;
import com.miguan.otk.model.bean.Battle;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(BattleRecordPresenter.class)
public class BattleRecordFragment extends BaseListFragment<BattleRecordPresenter, Battle> {

    @Override
    public BaseViewHolder<Battle> createViewHolder(ViewGroup parent, int viewType) {
        return new BattleRecordViewHolder(parent);
    }

}
