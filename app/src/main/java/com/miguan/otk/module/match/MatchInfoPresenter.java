package com.miguan.otk.module.match;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.model.constant.QQAction;
import com.miguan.otk.utils.LUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchInfoPresenter extends BaseDataFragmentPresenter<MatchInfoFragment, Match> {

    private Match mMatch;

    @Override
    protected void onCreate(MatchInfoFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        EventBus.getDefault().register(this);
        if (saveState != null && saveState.containsKey("match")) mMatch = saveState.getParcelable("match");
    }

    @Override
    protected void onCreateView(MatchInfoFragment view) {
        super.onCreateView(view);
        if (mMatch != null) publishData(mMatch);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(Match match) {
        publishData(match);
        mMatch = match;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        state.putParcelable("match", mMatch);
    }

    public void toUserList(int matchID, int type) {
        Intent i = new Intent(getView().getActivity(), CompetitorListActivity.class);
        i.putExtra("match_id", matchID);
        i.putExtra("type", type);
        getView().startActivity(i);
    }

    public void toQQGroup() {
        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse(QQAction.Group));
            getView().startActivity(intent);
        } catch (Exception e) {
            LUtils.toast("未安装手Q或安装的版本不支持");
        }
    }

}
