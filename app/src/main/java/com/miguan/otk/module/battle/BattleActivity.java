package com.miguan.otk.module.battle;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;

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

    @Bind(R.id.tv_battle_a_isready)
    TextView mTvAIsready;

    @Bind(R.id.dv_battle_b_avatar)
    SimpleDraweeView mDvBAvatar;

    @Bind(R.id.tv_battle_b_name)
    TextView mTvBName;

    @Bind(R.id.tv_battle_b_status)
    TextView mTvBStatus;

    @Bind(R.id.tv_battle_b_isready)
    TextView mTvBIsready;

    @Bind(R.id.tv_battle_round)
    TextView mTvRound;

    @Bind(R.id.tv_battle_score)
    TextView mTvScore;

    @Bind(R.id.tv_battle_status)
    TextView mTvStatus;

    @Bind(R.id.tv_battle_mode)
    TextView mTvMode;

    @Bind(R.id.btn_battle_status)
    Button mBtnStatus;

    private Fragment mFragment;

    private CountDownTimer mStatusTimer;

    private int mCurrentStatus = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_battle);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Battle battle) {
        if (battle.getBattle_status_user() == mCurrentStatus) return;

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
        setStatus(battle);
    }

    public void setStatus(Battle battle) {
        mCurrentStatus = battle.getBattle_status_user();
        if (battle.getBattle_status() == 1) {
            if (mStatusTimer != null) mStatusTimer.cancel();
            mStatusTimer = new CountDownTimer(battle.getReady_time() * 1000 - System.currentTimeMillis(), 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    mTvAStatus.setText(getPresenter().getFormatDate(millisUntilFinished));
                }

                @Override
                public void onFinish() {

                }

            }.start();
        }
        if (battle.getBattle_status_user() == 2) mTvAIsready.setVisibility(View.VISIBLE);
        else if (battle.getBattle_status_user() == 3) mTvBIsready.setVisibility(View.VISIBLE);
        else {
            mTvAIsready.setVisibility(View.GONE);
            mTvBIsready.setVisibility(View.GONE);
        }

        mBtnStatus.setText(battle.getStatus());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("battle", battle);

        if (mFragment != null) {
            ft.remove(mFragment);
        }

        if (battle.getBattle_status() == 1 || battle.getBattle_status() == 5 || battle.getBattle_status() == 0
                || battle.getUser_type() == 0 || battle.getUser_type() == 3) {
            mFragment = new ReadyFragment();
            mFragment.setArguments(bundle);
        } else {
            mFragment = new BattlingFragment();
            mFragment.setArguments(bundle);
        }
        ft.add(R.id.container_battle, mFragment);
        ft.commit();

//        switch (battle.getBattle_status()) {
//            case 1: // 准备
////                mBtnStatus.setOnClickListener(v -> getPresenter().ready());
//                LUtils.toast("准备");
//
//                break;
//            case 2: // pick
////                mBtnStatus.setOnClickListener();
//                break;
//            case 3: // ban
//                break;
//            case 4: // 比赛中
//                break;
//            case 5: // 结束
//
//                break;
//            case 6: //  争议
//                break;
//            default: // 未开始
//                break;
//        }
    }

}
