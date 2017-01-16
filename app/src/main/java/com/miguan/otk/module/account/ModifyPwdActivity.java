package com.miguan.otk.module.account;

import android.os.Bundle;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.ButterKnife;

@RequiresPresenter(ModifyPwdPresenter.class)
public class ModifyPwdActivity extends ChainBaseActivity<ModifyPwdPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modify_pwd);
        setToolbarTitle(R.string.title_activity_modify_pwd);
        ButterKnife.bind(this);
    }
}
