package com.miguan.otk.module.match;

import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.model.bean.User;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(VSListPresenter.class)
public class VSListFragment extends BeamListFragment<VSListPresenter, User> {


    @Override
    public BaseViewHolder<User> getViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }
}
