package com.miguan.otk.module.battle;

import android.graphics.Color;
import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.miguan.otk.adapter.viewholder.BattleRecordViewHolder;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.utils.LUtils;


/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(BattleRecordPresenter.class)
public class BattleRecordFragment extends BaseListFragment<BattleRecordPresenter, Battle> {

    @Override
    public BaseViewHolder<Battle> createViewHolder(ViewGroup parent, int viewType) {
        return new BattleRecordViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        DividerDecoration decoration = new DividerDecoration(Color.TRANSPARENT, LUtils.dp2px(8));
        return super.getListConfig().setItemDecoration(decoration);
    }
}
