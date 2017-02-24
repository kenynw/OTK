package com.miguan.otk.module.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.utils.LUtils;

import java.util.regex.Matcher;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(RegisterPresenter.class)
public class RegisterActivity extends ChainBaseActivity<RegisterPresenter> {

    @Bind(R.id.iv_register_back)
    ImageView mIvBack;

    @Bind(R.id.et_register_mobile)
    EditText mEtMobile;

    @Bind(R.id.et_register_captcha)
    EditText mEtCaptcha;

    @Bind(R.id.btn_register_captcha)
    Button mBtnCaptcha;

    @Bind(R.id.et_register_username)
    EditText mEtUsername;

    @Bind(R.id.et_register_password)
    EditText mEtPassword;

    @Bind(R.id.btn_register_submit)
    Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_register);
        setToolbarTitle(R.string.title_activity_register);
        ButterKnife.bind(this);

        mIvBack.setOnClickListener(v -> finish());
        mBtnCaptcha.setOnClickListener(v -> checkCaptcha());
        mBtnSubmit.setOnClickListener(v -> checkInput());
    }

    private void checkCaptcha() {
        Matcher matcher = Patterns.PHONE.matcher(mEtMobile.getText());
        if (!matcher.find() || mEtMobile.length() != 11) {
            LUtils.toast("电话格式不正确");
            return;
        }
        getPresenter().sendCaptcha(mEtMobile.getText().toString().trim());
    }

    private void checkInput() {
        Matcher phoneMatcher = Patterns.PHONE.matcher(mEtMobile.getText());
        if (!phoneMatcher.find() || mEtMobile.length() != 11) {
            LUtils.toast("手机号码格式不正确");
            return;
        }
        if (TextUtils.isEmpty(mEtUsername.getText())) {
            LUtils.toast("用户名不能为空");
            return;
        }
        getPresenter().register(
                mEtUsername.getText().toString().trim(),
                mEtMobile.getText().toString().trim(),
                mEtCaptcha.getText().toString().trim(),
                mEtPassword.getText().toString().trim()
        );
    }

}
