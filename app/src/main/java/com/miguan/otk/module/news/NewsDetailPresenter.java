package com.miguan.otk.module.news;

import android.content.Context;
import android.content.Intent;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.News;
import com.umeng.socialize.UMShareAPI;

/**
 * Copyright (c) 2016/12/30. LiaoPeiKun Inc. All rights reserved.
 */

public class NewsDetailPresenter extends BaseDataActivityPresenter<NewsDetailActivity, News> {

    public static final String EXTRA_ARTICLE_URL = "url";

    public static void start(Context context, String articleUrl) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(EXTRA_ARTICLE_URL, articleUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        UMShareAPI.get(getView()).onActivityResult(requestCode, resultCode, data);
    }
}
