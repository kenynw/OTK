package com.miguan.otk.widget;

import android.content.Context;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/2/17. LiaoPeiKun Inc. All rights reserved.
 */

public class BattleHeader extends LinearLayout {

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

    @Bind(R.id.tv_battle_a_status)
    TextView mTvAStatus;

    @Bind(R.id.tv_battle_b_status)
    TextView mTvBStatus;

    private CountDownTimer mStatusTimer;

    public BattleHeader(Context context) {
        super(context);
    }

    public BattleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BattleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) return;
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.include_batting_header, this);
        ButterKnife.bind(this, view);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mTvScore.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64 * dm.density);
    }

    public void setStatus(Battle battle) {
        mTvAName.setText(battle.getA_username());
        mDvAAvatar.setImageURI(Uri.parse(battle.getA_photo()));
        mTvBName.setText(battle.getB_username());
        mDvBAvatar.setImageURI(Uri.parse(battle.getB_photo()));

        mTvInfo.setText(battle.getBattle_mode() + " " + battle.getRound() + " (" + battle.getStatus() + ")");
        mTvScore.setText(battle.getBattle_score());

        mTvAStatus.setText(battle.getA_status());
        mTvBStatus.setText(battle.getB_status());

        if (battle.getReady_time() > 0) {
            if (mStatusTimer != null) mStatusTimer.cancel();
            mStatusTimer = new CountDownTimer(battle.getReady_time() * 1000 - System.currentTimeMillis(), 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    mTvStatus.setText(getFormatDate(millisUntilFinished));
                }

                @Override
                public void onFinish() {

                }

            }.start();
        }

    }

    private String getFormatDate(long time) {
        long hours = ((time % (1000 * 3600 * 24)) / (1000 * 3600));
        long minutes = ((time % (1000 * 3600)) / (1000 * 60));
        long seconds = (time % (1000 * 60) / 1000);
        return String.format("%1$02d:%2$02d:%3$02d", hours, minutes, seconds);
    }

}
