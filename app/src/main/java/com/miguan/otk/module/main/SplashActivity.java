package com.miguan.otk.module.main;

import android.content.Intent;
import android.os.Bundle;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.model.bean.Splash;

@RequiresPresenter(SplashPresenter.class)
public class SplashActivity extends BaseDataActivity<SplashPresenter, Splash> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setData(Splash splash) {
//        if (TextUtils.isEmpty(splash.getImg_and())) {
            startActivity(new Intent(this, MainActivity.class));
//        } else {
//            Intent intent = new Intent(this, AdActivity.class);
//            intent.putExtra("splash", splash);
//            startActivity(intent);
//        }
        finish();
    }

    @Override
    public void onError(Throwable e) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
