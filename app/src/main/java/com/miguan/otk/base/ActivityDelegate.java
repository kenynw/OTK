package com.miguan.otk.base;

import android.app.Activity;
import android.os.Bundle;

import com.dsk.chain.bijection.ActivityLifeCycleDelegate;

/**
 * Copyright (c) 2017/2/14. LiaoPeiKun Inc. All rights reserved.
 */

public class ActivityDelegate extends ActivityLifeCycleDelegate {

    public ActivityDelegate(Activity activity) {
        super(activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
