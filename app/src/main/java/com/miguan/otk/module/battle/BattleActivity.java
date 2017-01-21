package com.miguan.otk.module.battle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.module.match.SubmitShotActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BattlePresenter.class)
public class BattleActivity extends BaseDataActivity<BattlePresenter, Battle> {

    @Bind(R.id.dv_battle_a_avatar)
    SimpleDraweeView mDvAAvatar;

    @Bind(R.id.tv_battle_a_name)
    TextView mTvAName;

    @Bind(R.id.tv_battle_a_status)
    TextView mTvAStatus;

    @Bind(R.id.dv_battle_b_avatar)
    SimpleDraweeView mDvBAvatar;

    @Bind(R.id.tv_battle_b_name)
    TextView mTvBName;

    @Bind(R.id.tv_battle_b_status)
    TextView mTvBStatus;

    @Bind(R.id.tv_battle_round)
    TextView mTvRound;

    @Bind(R.id.tv_battle_score)
    TextView mTvScore;

    @Bind(R.id.tv_battle_status)
    TextView mTvStatus;

    @Bind(R.id.tv_battle_mode)
    TextView mTvMode;

    @Bind(R.id.btn_battle_judge)
    Button mBtnContact;

    @Bind(R.id.btn_battle_screenshot)
    Button mBtnScreenshot;

    @Bind(R.id.btn_battle_status)
    Button mBtnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_battle);
        ButterKnife.bind(this);

        mBtnContact.setOnClickListener(v -> startActivity(new Intent(this, ContactJudgeActivity.class)));
        mBtnScreenshot.setOnClickListener(v -> startActivity(new Intent(this, SubmitShotActivity.class)));
    }

    @Override
    public void setData(Battle battle) {
        setToolbarTitle(String.format(getString(R.string.label_battle_id), battle.getBattle_id()));

        mDvAAvatar.setImageURI(Uri.parse(battle.getA_photo()));
        mTvAName.setText(battle.getA_username());
        mTvAStatus.setText(battle.getA_status());

        mDvBAvatar.setImageURI(Uri.parse(battle.getB_photo()));
        mTvBName.setText(battle.getB_username());
        mTvBStatus.setText(battle.getB_status());

        mTvRound.setText(battle.getRound());
        mTvScore.setText(battle.getBattle_score());
        mTvStatus.setText(battle.getStatus());
        mTvMode.setText(battle.getBattle_mode());

        mBtnStatus.setText(battle.getStatus());
        switch (battle.getBattle_status()) {
            case 1: // 准备
                mBtnStatus.setOnClickListener(v -> getPresenter().ready());
                break;
            case 2: // pick
//                mBtnStatus.setOnClickListener();
                break;
            case 3: // ban
                break;
            case 4: // 比赛中
                break;
            case 5: // 结束
                break;
            case 6: //  争议
                break;
            default: // 未开始
                break;
        }
    }
}
