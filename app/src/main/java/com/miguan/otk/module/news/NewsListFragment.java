package com.miguan.otk.module.news;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.NewsViewHolder;
import com.miguan.otk.model.bean.News;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(NewsListPresenter.class)
public class NewsListFragment extends BaseListFragment<NewsListPresenter, News> {

    @Override
    public BaseViewHolder<News> createViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent, R.layout.item_list_news);
    }

}
