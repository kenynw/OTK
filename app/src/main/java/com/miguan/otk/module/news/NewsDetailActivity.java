package com.miguan.otk.module.news;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.News;
import com.miguan.otk.wxapi.ShareCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(NewsDetailPresenter.class)
public class NewsDetailActivity extends BaseDataActivity<NewsDetailPresenter, News> {

    @Bind(R.id.wv_news_detail)
    WebView mWebView;

    private News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_detail);
        ButterKnife.bind(this);

        mNews = getIntent().getParcelableExtra("news");
        if(mNews != null) mWebView.loadUrl(mNews.getUrl());
        else mWebView.loadUrl(getIntent().getStringExtra(NewsDetailPresenter.EXTRA_ARTICLE_URL));

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                getExpansionDelegate().showProgressBar();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getExpansionDelegate().hideProgressBar();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UMWeb umWeb = new UMWeb(mNews.getUrl());
        umWeb.setTitle(mNews.getTitle());
        umWeb.setThumb(new UMImage(this, R.mipmap.ic_launcher));
        umWeb.setDescription(mNews.getTitle() + "火热进行中！快上车，没时间解释了！");
        new ShareAction(this)
                .withMedia(umWeb)
                .setCallback(new ShareCallback())
                .share();
        return super.onOptionsItemSelected(item);
    }
}
