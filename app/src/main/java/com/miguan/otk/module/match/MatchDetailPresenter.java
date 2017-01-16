package com.miguan.otk.module.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Match;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
class MatchDetailPresenter extends BaseDataActivityPresenter<MatchDetailActivity, Match> {

    private int mMatchID;

    @Override
    protected void onCreate(MatchDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mMatchID = getView().getIntent().getIntExtra("match_id", 0);
    }

    @Override
    protected void onCreateView(MatchDetailActivity view) {
        super.onCreateView(view);
        MatchModel.getInstance().getMatchDetail(mMatchID).subscribe(getDataSubscriber());
    }

    public void share(Match match) {
        new ShareAction(getView())
                .withText(match.getTitle())
                .withMedia(new UMImage(getView(), R.mipmap.ic_launcher))
                .open();
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

        MatchRulesFragment rulesFragment1 = new MatchRulesFragment();
        rulesFragment1.setArguments(bundle);
        fragments.add(rulesFragment1);
        return fragments;
    }

}
