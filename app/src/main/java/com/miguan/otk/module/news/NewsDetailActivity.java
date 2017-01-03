package com.miguan.otk.module.news;

import android.os.Bundle;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.News;

@RequiresPresenter(NewsDetailPresenter.class)
public class NewsDetailActivity extends BaseDataActivity<NewsDetailPresenter, News> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_detail);
    }

}
