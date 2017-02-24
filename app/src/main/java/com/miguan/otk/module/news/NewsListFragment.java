package com.miguan.otk.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.miguan.otk.model.bean.News;
import com.miguan.otk.utils.LUtils;


/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(NewsListPresenter.class)
public class NewsListFragment extends BaseListFragment<NewsListPresenter, News> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getListView().setRecyclerPadding(0, LUtils.dp2px(5), 0, 0);
        getListView().setClipToPadding(false);
        getListView().setClipChildren(false);
        return view;
    }

    @Override
    public BaseViewHolder<News> createViewHolder(ViewGroup parent, int viewType) {
        return viewType == 1 ? new NewsViewHolder(parent) : new NewsMultiViewHolder(parent);
    }

    @Override
    public int getViewType(int position) {
        News news = getPresenter().getAdapter().getItem(position);
        return news.getImg().length == 1 ? 1 : 0;
    }

    @Override
    public ListConfig getListConfig() {
        SpaceDecoration itemDecoration = new SpaceDecoration(LUtils.dp2px(1));
        itemDecoration.setPaddingStart(true);
        return super.getListConfig().setItemDecoration(itemDecoration);
    }
}
