package com.miguan.otk.model;

import android.content.Context;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Screenshot;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.sgun.utils.LUtils;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

public class BattleModel extends AbsModel {

    public static BattleModel getInstance() {
        return getInstance(BattleModel.class);
    }

    @Override
    protected void onAppCreate(Context context) {
        super.onAppCreate(context);
    }

    /**
     * 我的对战记录
     *
     * @param page 当前页数
     * @return
     */
    public Observable<List<Battle>> getBattleList(int page) {
        return ServicesClient.getServices().againstList(LUtils.getPreferences().getString("token", ""), page).compose(new DefaultTransform<>());
    }

    public Observable<Battle> getBattleDetail(int battleID) {
        return ServicesClient.getServices()
                .battleDetail(LUtils.getPreferences().getString("token", ""), battleID)
                .compose(new DefaultTransform<>());
    }

    /**
     * 对战准备
     *
     * @param matchID 赛事ID
     * @return
     */
    public Observable<Battle> ready(int matchID) {
        return ServicesClient.getServices().ready(LUtils.getPreferences().getString("token", ""), matchID).compose(new DefaultTransform<>());
    }

    /**
     * 对战Pick
     *
     * @param battleID 赛事ID
     * @param map      pick列表 car1 car2 car3 car4
     * @return
     */
    public Observable<Battle> pick(int battleID, Map<String, Integer> map) {
        return ServicesClient.getServices().pick(
                LUtils.getPreferences().getString("token", ""), battleID, map).compose(new DefaultTransform<>());
    }

    /**
     * 对战Ban
     *
     * @param battleID 赛事ID
     * @param ban      ban
     * @return
     */
    public Observable<Battle> ban(int battleID, Integer ban) {
        return ServicesClient.getServices().ban(LUtils.getPreferences().getString("token", ""), battleID, ban).compose(new DefaultTransform<>());
    }

    /**
     * 对战提交结果
     *
     * @param battleID 赛事ID
     * @param winnerID ban
     * @return
     */
    public Observable<Battle> submit(int battleID, Integer winnerID) {
        return ServicesClient.getServices().submitResult(LUtils.getPreferences().getString("token", ""), battleID, winnerID).compose(new DefaultTransform<>());
    }

    /**
     * 重置对战结果
     *
     * @param battleID 赛事ID
     * @return
     */
    public Observable<Battle> resubmit(int battleID) {
        return ServicesClient.getServices().resubmitResult(LUtils.getPreferences().getString("token", ""), battleID).compose(new DefaultTransform<>());
    }

    /**
     * 对战截图信息
     *
     * @param battleID 赛事ID
     * @return
     */
    public Observable<Screenshot> uploadInfo(int battleID, String role) {
        return ServicesClient.getServices().battleUploadInfo(LUtils.getPreferences().getString("token", ""), battleID, role).compose(new DefaultTransform<>());
    }

    /**
     * 提交对战截图
     *
     * @param battleID 赛事ID
     * @return
     */
    public Observable<Battle> upload(String uriPath, int battleID, String kind) {
        return ServicesClient.getServices().battleUpload(LUtils.getPreferences().getString("token", ""), battleID, kind, uriPath).compose(new DefaultTransform<>());
    }

}
