package com.miguan.otk.model;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Splash;
import com.miguan.otk.model.bean.Version;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.module.common.UpdateService;
import com.miguan.otk.utils.LUtils;

import rx.Observable;

/**
 * Copyright (c) 2017/1/13. LiaoPeiKun Inc. All rights reserved.
 */

public class CommonModel extends AbsModel {

    public static CommonModel getInstance() {
        return getInstance(CommonModel.class);
    }

    public Observable<Splash> getSplashImage(String type, Integer client) {
        return ServicesClient.getServices().splash(type, client).compose(new DefaultTransform<>());
    }

    public void update(Context context) {
        ServicesClient.getServices().checkUpdate(LUtils.getAppVersionName())
                .compose(new DefaultTransform<>())
                .subscribe(new ServicesResponse<Version>() {
                    @Override
                    public void onNext(Version version) {
                        if (!version.getVersion_number().equals(LUtils.getAppVersionName()))
                            showUpdateDialog(context, version);
                    }
                });
    }

    public void checkUpdate(Context context) {
        ServicesClient.getServices().checkUpdate(LUtils.getAppVersionName())
                .compose(new DefaultTransform<>())
                .subscribe(new ServicesResponse<Version>() {
                    @Override
                    public void onNext(Version version) {
                        super.onNext(version);
                        if (version.getType() == 0) {
                            LUtils.log("已经是最新版本了");
                        } else if (!version.getVersion_number().equals(LUtils.getAppVersionName())) {
                            showUpdateDialog(context, version);
                        }
                    }
                });
    }

    private void showUpdateDialog(Context context, Version version) {
        new AlertDialog.Builder(context)
                .setTitle("发现新版本：" + version.getVersion_number())
                .setMessage(version.getUpgrade_content())
                .setNegativeButton(version.getType() == 1 ? "下次再说" : "", null)
                .setPositiveButton("马上更新", (dialog, which) -> {
                    LUtils.log("开始下载");
                    Intent intent = new Intent(context, UpdateService.class);
                    intent.putExtra("title", "正在下载" + context.getString(R.string.app_name));
                    intent.putExtra("url", version.getApk_url());
                    intent.putExtra("path", findDownLoadDirectory());
                    intent.putExtra("name", context.getString(R.string.app_name) + "v" + version.getVersion_number() + ".apk");
                    context.startService(intent);
                })
                .show();
    }

    private String findDownLoadDirectory(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return Environment.getExternalStorageDirectory() + "/" + "download/";
        }else{
            return Environment.getRootDirectory() + "/" + "download/";
        }
    }


}
