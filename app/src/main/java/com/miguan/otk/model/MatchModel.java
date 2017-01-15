package com.miguan.otk.model;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.Schedule;
import com.miguan.otk.model.bean.Home;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.sgun.utils.LUtils;

import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2017/1/6. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchModel extends AbsModel {

    public static MatchModel getInstance() {
        return getInstance(MatchModel.class);
    }

    public Observable<Home> getHome() {
        return ServicesClient.getServices().homeMatch(LUtils.getPreferences().getString("token", "")).compose(new DefaultTransform<>());
    }

    /**
     * 赛事详情
     * @param matchID 赛事ID
     * @return
     */
    public Observable<Match> getMatchDetail(int matchID) {
        return ServicesClient.getServices().matchDetail(LUtils.getPreferences().getString("token", ""), matchID).compose(new DefaultTransform<>());
    }

    /**
     * 赛事参加用户列表
     * @param matchID 赛事ID
     * @return
     */
    public Observable<List<User>> getCompetitors(int matchID, int type) {
        return ServicesClient.getServices().competitors(matchID, type).compose(new DefaultTransform<>());
    }

    /**
     * 赛事参加用户列表
     * @param matchID 赛事ID
     * @return
     */
    public Observable<Match> getCompetitionRule(int matchID) {
        return ServicesClient.getServices().competitionRule(matchID).compose(new DefaultTransform<>());
    }

    /**
     * 赛事对战表
     * @param matchID 赛事ID
     * @return
     */
    public Observable<Schedule> getCompetitionSchedule(int matchID, int round) {
        return ServicesClient.getServices().competitionSchedule(matchID, round).compose(new DefaultTransform<>());
    }

    /**
     * 我的参赛列表
     * @param page 当前页数
     * @return
     */
    public Observable<List<Match>> getMyMatchList(int page) {
        return ServicesClient.getServices().myMatchList(LUtils.getPreferences().getString("token", ""), page).compose(new DefaultTransform<>());
    }

    /**
     * 我的对战记录
     * @param page 当前页数
     * @return
     */
    public Observable<List<Schedule>> getAgainstList(int page) {
        return ServicesClient.getServices().againstList(LUtils.getPreferences().getString("token", ""), page).compose(new DefaultTransform<>());
    }

}
