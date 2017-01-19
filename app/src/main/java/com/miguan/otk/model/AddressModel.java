package com.miguan.otk.model;

import android.content.Context;
import android.content.res.AssetManager;

import com.dsk.chain.model.AbsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miguan.otk.model.bean.Area;
import com.miguan.otk.model.services.DefaultTransform;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2017/1/19. LiaoPeiKun Inc. All rights reserved.
 */

public class AddressModel extends AbsModel {

    private AssetManager mAssetManager;

    public static AddressModel getInstance() {
        return getInstance(AddressModel.class);
    }

    @Override
    protected void onAppCreate(Context context) {
        mAssetManager = context.getAssets();
    }

    public Observable<List<Area>> getAreaList() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = mAssetManager.open("ChineseCities.json");
            byte[] by = new byte[is.available()];
            int len = -1;
            while ((len = is.read(by)) != -1) {
                sb.append(new String(by, 0, len, "utf-8"));
            }
            is.close();
            List<Area> list = new Gson().fromJson(sb.toString(), new TypeToken<List<Area>>(){}.getType());
            return Observable.just(list).compose(new DefaultTransform<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
