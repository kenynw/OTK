package com.miguan.otk.module.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SignInPresenter.class)
public class SignInActivity extends BaseDataActivity<SignInPresenter, User> {

    @Bind(R.id.tv_sign_series_desc)
    TextView mTvSeriesDesc;

    @Bind(R.id.btn_sign_now)
    Button mBtnSign;

    @Bind(R.id.tv_sign_rules)
    TextView mTvRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_sign_in);
        setToolbarTitle(R.string.title_activity_sign_in);
        ButterKnife.bind(this);

        mBtnSign.setOnClickListener(v -> getPresenter().sign());
    }

}
