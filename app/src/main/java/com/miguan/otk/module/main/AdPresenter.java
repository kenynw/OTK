package com.miguan.otk.module.main;

import android.content.Intent;

import com.dsk.chain.bijection.Presenter;

/**
 * Copyright (c) 2017/1/13. LiaoPeiKun Inc. All rights reserved.
 */

public class AdPresenter extends Presenter<AdActivity> {

    public void skip() {
        getView().startActivity(new Intent(getView(), MainActivity.class));
        getView().finish();
    }

}
