package com.miguan.otk.module.news;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.News;

/**
 * Copyright (c) 2016/12/30. LiaoPeiKun Inc. All rights reserved.
 */

public class NewsDetailPresenter extends BaseDataActivityPresenter<NewsDetailActivity, News> {

    private String mUrl;

    @Override
    protected void onCreate(NewsDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUrl = getView().getIntent().getStringExtra("url");
    }

}
