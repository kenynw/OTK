package com.miguan.otk.module.main;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Splash;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(AdPresenter.class)
public class AdActivity extends ChainBaseActivity<AdPresenter> {

    @Bind(R.id.dv_ad_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.btn_ad_skip)
    Button mBtnSkip;

    private CountDownTimer mDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_ad);
        ButterKnife.bind(this);

        mBtnSkip.setOnClickListener(v -> getPresenter().skip());
        setData();
    }

    private void setData() {
        Splash splash = getIntent().getParcelableExtra("splash");
        mDvImage.setImageURI(splash.getImg_and());
        mBtnSkip.setText(String.format(getString(R.string.btn_skip), splash.getShow_time()));
        mDownTimer = new CountDownTimer(splash.getShow_time() * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mBtnSkip.setText(String.format(getString(R.string.btn_skip), millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                getPresenter().skip();
            }

        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownTimer.cancel();
    }

}
