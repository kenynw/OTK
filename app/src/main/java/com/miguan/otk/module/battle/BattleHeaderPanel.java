package com.miguan.otk.module.battle;

import android.app.Activity;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.utils.DateUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/2/28. LiaoPeiKun Inc. All rights reserved.
 */

public class BattleHeaderPanel {

    @Bind(R.id.tv_battle_a_name)
    TextView mTvAName;

    @Bind(R.id.dv_battle_a_avatar)
    SimpleDraweeView mDvAAvatar;

    @Bind(R.id.tv_battle_a_qq)
    TextView mTvAQQ;

    @Bind(R.id.tv_battle_b_name)
    TextView mTvBName;

    @Bind(R.id.dv_battle_b_avatar)
    SimpleDraweeView mDvBAvatar;

    @Bind(R.id.tv_battle_b_qq)
    TextView mTvBQQ;

    @Bind(R.id.tv_battle_info)
    TextView mTvInfo;

    @Bind(R.id.tv_battle_score)
    TextView mTvScore;

    @Bind(R.id.tv_battle_status)
    TextView mTvStatus;

    private CountDownTimer mStatusTimer;

    private Activity mActivity;

    private View mView;

    private Battle mBattle;

    public BattleHeaderPanel(Activity activity, View view, Battle battle) {
        mActivity = activity;
        mView = view;

        initViews();
        load(battle);
    }

    private void initViews() {
        ButterKnife.bind(this, mView);

        DisplayMetrics dm = mActivity.getResources().getDisplayMetrics();
        mTvScore.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64 * dm.density);
    }

    public void load(Battle battle) {
        if (mBattle != null && mBattle.getBattle_status_user() == battle.getBattle_status_user()) {
            return;
        }
        mBattle = battle;

        mTvAName.setText(battle.getA_username());
        mDvAAvatar.setImageURI(Uri.parse(battle.getA_photo()));
        mTvAQQ.setText(battle.getA_qq());
        mTvBName.setText(battle.getB_username());
        mDvBAvatar.setImageURI(Uri.parse(battle.getB_photo()));
        mTvBQQ.setText(battle.getB_qq());

        mTvInfo.setText(battle.getBattle_mode() + " " + battle.getRound() + " (" + battle.getStatus() + ")");
        mTvScore.setText(battle.getBattle_score());

        if (battle.getReady_time() > 0) {
            if (mStatusTimer != null) mStatusTimer.cancel();
            mStatusTimer = new CountDownTimer(battle.getReady_time() * 1000 - System.currentTimeMillis(), 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    mTvStatus.setText(DateUtils.getFormatTime(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    EventBus.getDefault().post(mBattle);
                }

            }.start();
        }

    }

}
