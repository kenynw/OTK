package com.miguan.otk.module.settings;

import android.os.Bundle;
import android.widget.TextView;

import com.dsk.chain.bijection.BeamBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(AboutPresenter.class)
public class AboutActivity extends BeamBaseActivity<AboutPresenter> {

    @Bind(R.id.tv_about_version)
    TextView mTvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_about);
        setToolbarTitle(R.string.btn_about_us);
        ButterKnife.bind(this);

        mTvVersion.setText(String.format("v %s", LUtils.getAppVersionName()));
    }

}