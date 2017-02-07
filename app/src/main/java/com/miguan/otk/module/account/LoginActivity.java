package com.miguan.otk.module.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends ChainBaseActivity<LoginPresenter> {

    @Bind(R.id.et_login_username)
    EditText mEtMobile;

    @Bind(R.id.et_login_password)
    EditText mEtPassword;

    @Bind(R.id.tv_login_forgot)
    TextView mTvForgot;

    @Bind(R.id.tv_login_register)
    TextView mTvRegister;

    @Bind(R.id.btn_login_submit)
    Button mBtnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);
        setToolbarTitle(R.string.title_activity_login);
        ButterKnife.bind(this);

        mTvForgot.setOnClickListener(v -> startActivity(new Intent(this, ForgotActivity.class)));
        mTvRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        mBtnLogin.setOnClickListener(v -> checkInput());
//        mTvQQ.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.QQ));
//        mTvWx.setOnClickListener(v -> getPresenter().doOauthVerify(SHARE_MEDIA.WEIXIN));
    }

    private void checkInput() {
        if (TextUtils.isEmpty(mEtMobile.getText())) {
            LUtils.toast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEtPassword.getText())) {
            LUtils.toast("请输入密码");
            return;
        }

        getPresenter().login(mEtMobile.getText().toString().trim(), mEtPassword.getText().toString().trim());
    }

}
