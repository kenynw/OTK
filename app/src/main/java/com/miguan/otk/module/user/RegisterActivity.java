package com.miguan.otk.module.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;

import com.dsk.chain.bijection.BeamBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.sgun.utils.LUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(RegisterPresenter.class)
public class RegisterActivity extends BeamBaseActivity<RegisterPresenter> {

    @Bind(R.id.et_register_mobile)
    EditText mEtMobile;

    @Bind(R.id.et_register_password)
    EditText mEtPassword;

    @Bind(R.id.et_register_captcha)
    EditText mEtCaptcha;

    @Bind(R.id.btn_register_caption)
    Button mBtnCaptcha;

    @Bind(R.id.btn_register_submit)
    Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_register);
        setToolbarTitle(R.string.title_activity_register);
        ButterKnife.bind(this);

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
        if (mEtCaptcha.length() != 6) {
            LUtils.toast("验证码不能小于6位");
            return;
        }
        if (TextUtils.isEmpty(mEtPassword.getText()) || mEtPassword.length() < 8) {
            LUtils.toast("密码不少于8位");
            return;
        }
        getPresenter().register(
                mEtMobile.getText().toString().trim(),
                mEtCaptcha.getText().toString().trim(),
                mEtPassword.getText().toString().trim()
        );
    }

}
