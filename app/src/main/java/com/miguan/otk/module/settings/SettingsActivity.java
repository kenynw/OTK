package com.miguan.otk.module.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SettingsPresenter.class)
public class SettingsActivity extends ChainBaseActivity<SettingsPresenter> {

    @Bind(R.id.ly_setting_clear)
    FrameLayout mLyClear;

    @Bind(R.id.tv_setting_clear_cache)
    TextView mTvCache;

    @Bind(R.id.tv_setting_about_us)
    TextView mTvAbout;

    @Bind(R.id.btn_setting_logout)
    Button mBtnLogout;

    @Bind(R.id.ly_setting_update)
    FrameLayout mLyUpdate;

    @Bind(R.id.tv_setting_version)
    TextView mTvVersion;

    @Bind(R.id.tv_setting_push)
    TextView mTvPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setToolbarTitle(R.string.title_activity_settings);
        ButterKnife.bind(this);

        mLyClear.setOnClickListener(v -> showDialog());
        mTvAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
        mLyUpdate.setOnClickListener(v -> getPresenter().checkUpdate());
        mBtnLogout.setOnClickListener(v -> getPresenter().logout());
        mTvPush.setOnClickListener(v -> startActivity(new Intent(this, SettingPushActivity.class)));
    }

    public void setData(String cache, String version) {
        mTvCache.setText(cache);
        mTvVersion.setText(String.format(getString(R.string.label_current_version), version));
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_clear_cache)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_ok, (dialog, which) -> getPresenter().clearCache())
                .show();
    }

}
