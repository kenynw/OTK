package com.miguan.otk.module.news;

import android.content.Intent;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.News;
import com.umeng.socialize.UMShareAPI;

/**
 * Copyright (c) 2016/12/30. LiaoPeiKun Inc. All rights reserved.
 */

public class NewsDetailPresenter extends BaseDataActivityPresenter<NewsDetailActivity, News> {

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        UMShareAPI.get(getView()).onActivityResult(requestCode, resultCode, data);
    }
}
