package com.miguan.otk.module.news;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.News;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class NewsListPresenter extends BaseListFragmentPresenter<NewsListFragment, News> {

    @Override
    protected void onCreateView(NewsListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<News> list = new ArrayList<>();
        for (int i=0; i<15; i++) {
            News news = new News();
            news.setTitle("NEST2016总决赛完美落幕 冠军之夜荣耀绽放厦门");
            news.setDate("2016-11-21");
            news.setImage("http://static.otkpk.com/ueditor/php/upload/image/20161121/1479719270181900.jpg");
            list.add(news);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        List<News> list = new ArrayList<>();
        for (int i=0; i<getCurPage()*10; i++) {
            News news = new News();
            news.setTitle("NEST2016总决赛完美落幕 冠军之夜荣耀绽放厦门");
            news.setDate("2016-11-21");
            news.setImage("http://static.otkpk.com/ueditor/php/upload/image/20161121/1479719270181900.jpg");
            list.add(news);
        }
        Observable.just(list).unsafeSubscribe(getMoreSubscriber());
    }
}
