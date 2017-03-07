package com.miguan.otk.module.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.model.services.ServiceException;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.module.battle.BattleActivity;
import com.miguan.otk.module.main.MainActivity;
import com.miguan.otk.module.user.GameAccountAddActivity;
import com.miguan.otk.utils.LUtils;
import com.miguan.otk.wxapi.ShareCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
public class MatchDetailPresenter extends BaseDataActivityPresenter<MatchDetailActivity, Match> implements SwipeRefreshLayout.OnRefreshListener {

    private int mMatchID;

    @Override
    protected void onCreate(MatchDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mMatchID = getView().getIntent().getIntExtra("match_id", 0);
    }

    @Override
    protected void onCreateView(MatchDetailActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getMatchDetail(mMatchID).unsafeSubscribe(getDataSubscriber());
    }

    public void share() {
        if (getData() != null) {
            UMWeb umWeb = new UMWeb(getData().getShare_url());
            umWeb.setTitle(getData().getTitle());
            umWeb.setThumb(new UMImage(getView(), R.mipmap.ic_launcher));
            umWeb.setDescription(getData().getTitle() + "火热进行中！快上车，没时间解释了！");
            new ShareAction(getView())
                    .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                            SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                    .withMedia(umWeb)
                    .setCallback(new ShareCallback())
                    .open();
        }
    }

    public void enter(String pwd, String code) {
        MatchModel.getInstance().enter(mMatchID, pwd, code).unsafeSubscribe(new ServicesResponse<Battle>() {
            @Override
            public void onNext(Battle battle) {
                getView().setEnrolled();
                onRefresh();
            }

            @Override
            public void onError(Throwable e) {
                doError(e);
            }
        });
    }

    public void sign() {
        MatchModel.getInstance().sign(mMatchID).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                getView().setSigned();
            }

            @Override
            public void onError(Throwable e) {
                doError(e);
            }
        });
    }

    public void getBattleID() {
        MatchModel.getInstance().getBattleID(mMatchID).unsafeSubscribe(new ServicesResponse<Battle>() {
            @Override
            public void onNext(Battle battle) {
                if (battle.getBattle_id() > 0) {
                    Intent intent = new Intent(getView(), BattleActivity.class);
                    intent.putExtra("battle_id", battle.getBattle_id());
                    getView().startActivity(intent);
                }
            }
        });
    }

    private void doError(Throwable e) {
        if (e instanceof ServiceException) {
            ServiceException exception = ((ServiceException) e);

            if (exception.getCode() == 101) {
                Intent intent = new Intent(getView(), GameAccountAddActivity.class);
                getView().startActivity(intent);
            } else if (exception.getCode() == 102) {
                new AlertDialog.Builder(getView())
                        .setMessage("当前赛事已结束，逛逛其他地方吧～")
                        .setPositiveButton(R.string.btn_ok, (dialog, which) -> {
                            Intent intent = new Intent(getView(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            getView().startActivity(intent);
                        })
                        .show();
            } else {
                LUtils.log(exception.getMsg());
            }

        } else {
            LUtils.log("网络错误");
        }
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        UMShareAPI.get(getView()).onActivityResult(requestCode, resultCode, data);
    }

    public List<Fragment> getFragments() {
        Bundle bundle = new Bundle();
        bundle.putInt("match_id", mMatchID);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MatchInfoFragment());

        MatchRulesFragment rulesFragment = new MatchRulesFragment();
        rulesFragment.setArguments(bundle);
        fragments.add(rulesFragment);

        ScheduleListFragment scheduleFragment = new ScheduleListFragment();
        scheduleFragment.setArguments(bundle);
        fragments.add(scheduleFragment);

        return fragments;
    }

}
