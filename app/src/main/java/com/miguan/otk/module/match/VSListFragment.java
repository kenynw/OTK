package com.miguan.otk.module.match;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.VSViewHolder;
import com.miguan.otk.model.bean.VS;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(VSListPresenter.class)
public class VSListFragment extends BaseListFragment<VSListPresenter, VS> {

    @Override
    public BaseViewHolder<VS> createViewHolder(ViewGroup parent, int viewType) {
        return new VSViewHolder(parent);
    }

    @Override
    public int getLayout() {
        return R.layout.match_fragment_vs_list;
    }
}
