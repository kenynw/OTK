package com.miguan.otk.module.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SettingsPresenter.class)
public class SettingsActivity extends ChainBaseActivity<SettingsPresenter> {

    @Bind(R.id.btn_setting_clear_cache)
    Button mBtnClear;

    @Bind(R.id.btn_setting_about_us)
    Button mBtnAbout;

    @Bind(R.id.btn_setting_logout)
    Button mBtnLogout;

    @Bind(R.id.btn_check_update)
    Button mBtnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setToolbarTitle(R.string.title_activity_settings);
        ButterKnife.bind(this);

        mBtnClear.setOnClickListener(v -> showDialog());
        mBtnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
        mBtnUpdate.setOnClickListener(v -> getPresenter().checkUpdate());
        mBtnLogout.setOnClickListener(v -> getPresenter().logout());
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_clear_cache)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_ok, (dialog, which) -> getPresenter().clearCache())
                .show();
    }

}
