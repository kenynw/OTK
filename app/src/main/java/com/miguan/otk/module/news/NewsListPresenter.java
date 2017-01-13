package com.miguan.otk.module.news;

import android.content.Intent;
import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.NewsModel;
import com.miguan.otk.model.bean.News;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class NewsListPresenter extends BaseListFragmentPresenter<NewsListFragment, News> {

    private int mType = 1;

    @Override
    protected void onCreate(NewsListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (view.getArguments() != null) {
            mType = view.getArguments().getInt("type");
        }
    }

    @Override
    protected void onCreateView(NewsListFragment view) {
        super.onCreateView(view);
        onRefresh();
        getAdapter().setOnItemClickListener(position -> {
            News news = getAdapter().getItem(position);
            Intent intent = new Intent(getView().getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", news);
            getView().startActivity(intent);
        });
    }

    @Override
    public void onRefresh() {
        NewsModel.getInstance().getNewsList(mType, 1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        NewsModel.getInstance().getNewsList(mType, getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
