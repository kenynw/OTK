package com.miguan.otk.model.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2017/1/23. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchDetailServices extends Service {

    public static final String ACTION = "com.sgun.utils.LUtils.MatchDetailServices";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LUtils.log("onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        new PollingThread(1).start();
        LUtils.log("onStart");
    }

    public static class PollingThread extends Thread {
        private int mStatus;

        public PollingThread(int status) {
            mStatus = status;
        }

        @Override
        public void run() {
            LUtils.log("polling");
        }
    }

}
