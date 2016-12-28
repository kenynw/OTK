package com.miguan.otk.module.user;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;

public class SignInActivity extends BaseDataActivity<SignInPresenter, User> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_sign_in);
    }

}
