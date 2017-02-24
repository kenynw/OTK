package com.miguan.otk.module.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BindAlipayPresenter.class)
public class BindAlipayActivity extends ChainBaseActivity<BindAlipayPresenter> {

    @Bind(R.id.et_bind_mobile)
    EditText mEtMobile;

    @Bind(R.id.et_bind_captcha)
    EditText mEtCaptcha;

    @Bind(R.id.btn_bind_captcha)
    Button mBtnCaptcha;

    @Bind(R.id.et_bind_account)
    EditText mEtAccount;

    @Bind(R.id.btn_bind_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_bind_alipay);
        setToolbarTitle(R.string.btn_bind_alipay);
        ButterKnife.bind(this);

        mBtnCaptcha.setOnClickListener(v -> checkCaptcha());
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void checkCaptcha() {
        if (TextUtils.isEmpty(mEtMobile.getText())) {
            LUtils.toast("请输入手机号码");
            return;
        }

        mBtnCaptcha.setEnabled(false);
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mBtnCaptcha.setText((millisUntilFinished / 1000) + "秒");
            }

            @Override
            public void onFinish() {
                mBtnCaptcha.setEnabled(true);
                mBtnCaptcha.setText(R.string.btn_send_captcha);
            }
        }.start();

        getPresenter().sendCaptcha(mEtMobile.getText().toString().trim());
    }

    private void checkInput() {
        if (TextUtils.isEmpty(mEtCaptcha.getText())) {
            LUtils.toast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(mEtAccount.getText())) {
            LUtils.toast("请输入帐号");
            return;
        }
        getPresenter().save(
                mEtMobile.getText().toString().trim(),
                mEtAccount.getText().toString().trim(),
                mEtCaptcha.getText().toString().trim()
        );
    }

}
