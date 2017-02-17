package com.miguan.otk.module.battle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.adapter.BanPickAdapter;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Hero;
import com.miguan.otk.widget.SectionView;
import com.sgun.utils.LUtils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BattlePresenter.class)
public class BattleActivity extends BaseDataActivity<BattlePresenter, Battle> {

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

    @Bind(R.id.section_battle_status)
    SectionView mSection;

    @Bind(R.id.btn_battle_status_save)
    Button mBtnSave;

    private Fragment mFragment;

    private CountDownTimer mStatusTimer;

    private int mCurrentStatus = -1;

    @Bind(R.id.include_battle_operation)
    View mLyOperation;

    @Bind(R.id.tv_battling_opponent)
    TextView mTvOpponent;

    @Bind(R.id.btn_pick_copy)
    Button mBtnCopy;

    @Bind(R.id.tv_battling_judge)
    TextView mTvContact;

    @Bind(R.id.tv_battling_screenshot)
    TextView mTvScreenshot;

    @Bind(R.id.tv_battling_chatroom)
    TextView mTvChatroom;

    @Bind(R.id.tv_battle_status_title)
    TextView mTvTitle;

    @Bind(R.id.tv_battle_status_notice)
    TextView mTvNotice;

    @Bind(R.id.tv_battle_status_desc)
    TextView mTvDesc;

    @Bind(R.id.grid_battling_list)
    ExGridView mGridHeros;

    @Bind(R.id.layout_battling_ongoing)
    LinearLayout mLyOngoing;

    @Bind(R.id.tv_battling_a_account)
    TextView mTvAAccount;

    @Bind(R.id.grid_battling_a_bans)
    ExGridView mGridABans;

    @Bind(R.id.tv_battling_b_account)
    TextView mTvBAccount;

    @Bind(R.id.grid_battling_b_bans)
    ExGridView mGridBBans;

    @Bind(R.id.ly_battling_submit_result)
    LinearLayout mLyResult;

    @Bind(R.id.btn_battling_im_win)
    Button mBtnImWin;

    @Bind(R.id.btn_battling_im_lost)
    Button mBtnImLost;

    private List<Hero> mSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_battle);
        ButterKnife.bind(this);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mTvScore.setTextSize(TypedValue.COMPLEX_UNIT_PX, 64 * dm.density);
    }

    @Override
    public void setData(Battle battle) {
        if (battle.getBattle_status_user() == mCurrentStatus) return;
        mCurrentStatus = battle.getBattle_status_user();

        setToolbarTitle(String.format(getString(R.string.label_battle_id), battle.getBattle_id()));

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
                    mTvStatus.setText(getPresenter().getFormatDate(millisUntilFinished));
                }

                @Override
                public void onFinish() {

                }

            }.start();
        }

//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("battle", battle);
//        if (mFragment != null) {
//            ft.remove(mFragment);
//        }
//        if (battle.getBattle_status() == 1 || battle.getBattle_status() == 5 || battle.getBattle_status() == 0
//                || battle.getUser_type() == 0 || battle.getUser_type() == 3) {
            setStatus(battle);
//        } else {
//            mFragment = new BattlingFragment();
//            mFragment.setArguments(bundle);
//        }
//        ft.add(R.id.container_battle, mFragment);
//        ft.commit();

    }

    public void setStatus(Battle battle) {

        // 观战模式 用户类型为游客或未参赛
        if (battle.getUser_type() == 0 || battle.getUser_type() == 3) {
            setSimpleDesc();
            mBtnSave.setVisibility(View.GONE);
            mSection.setTitle("比赛阶段")
                    .setDesc(String.format("%s阶段比赛正在进行中", battle.getBattle_times()));
            return;
        }

        // 等待对手生成
        if (battle.getBattle_status() == 0) {
            setSimpleDesc();
            mSection.setTitle("等待对手生成")
                    .setDesc("您的对手还没有结束当前比赛，请耐心等待");
            mBtnSave.setVisibility(View.GONE);
            return;
        }

        // 准备阶段
        if (battle.getBattle_status() == 1) {
            setSimpleDesc();
            mSection.setTitle(R.string.text_prepare_phase)
                    .setDesc(R.string.text_ready_desc);
            if ((battle.getUser_type() == 1 && battle.getBattle_status_user() == 2) || (battle.getUser_type() == 2 && battle.getBattle_status_user() == 3)) {
                mBtnSave.setText("已准备");
                mBtnSave.setEnabled(false);
            } else {
                mBtnSave.setText("准备");
                mBtnSave.setOnClickListener(v -> getPresenter().ready());
            }
            return;
        }

        // 结束
        if (battle.getBattle_status() == 5) {
            setSimpleDesc();
            mSection.setTitle(R.string.text_battle_ended)
                    .setDesc(String.format(getString(R.string.text_battle_ended_desc), battle.getWinner_id() == battle.getA_user_id() ? battle.getA_username() : (battle.getWinner_id() == battle.getB_user_id() ? battle.getB_username() : "无结果")));

            if (battle.getWinner_id() == 1 && battle.getNext_battle_id() > 0) {
                mBtnSave.setText("进入下一轮");
                mBtnSave.setOnClickListener(v -> EventBus.getDefault().post(battle));
            } else if (battle.getIs_end()) {
                mBtnSave.setVisibility(View.GONE);
            } else {
                mBtnSave.setText("结束");
                mBtnSave.setOnClickListener(v -> {
                    Intent intent = new Intent(this, BattleActivity.class);
                    intent.putExtra("battle_id", battle.getBattle_id());
                    startActivity(intent);
                });
            }
            return;
        }

        mLyOperation.setVisibility(View.VISIBLE);
        mTvOpponent.setText(battle.getUser_type() == 1 ? battle.getB_gameaccount() : battle.getA_gameaccount());

        // 比赛处于pick阶段
        if (battle.getBattle_status() == 2) {
            mSection.setVisibility(View.VISIBLE);
            mBtnSave.setVisibility(View.VISIBLE);
            if (battle.getBattle_status_user() == 5 && battle.getUser_type() == 1
                    || battle.getBattle_status_user() == 6 && battle.getUser_type() == 2) {
                mSection.setTitle(R.string.text_pick_title)
                        .setDesc(R.string.text_picked_desc)
                        .setNotice("");
                mGridHeros.setVisibility(View.GONE);
                mBtnSave.setText("已提交");
                mBtnSave.setEnabled(false);
            } else {
                mGridHeros.setVisibility(View.VISIBLE);

                mSection.setTitle(R.string.text_pick_title)
                        .setNotice(R.string.text_pick_notice)
                        .setDesc(R.string.text_pick_desc);

                BanPickAdapter adapter = new BanPickAdapter(this, BanPickAdapter.MODE_PICK);
                adapter.setOnItemClickListener(hero -> {
                    if (mSelected.contains(hero)) {
                        mSelected.remove(hero);
                        adapter.select(hero);
                    } else {
                        if ((battle.getBattle_mode().equals("BO3") && mSelected.size() >= 3)
                                || (battle.getBattle_mode().equals("BO5") && mSelected.size() >= 4)) {
                            LUtils.toast("已达最大数量");
                        } else {
                            mSelected.add(hero);
                            adapter.select(hero);
                        }
                    }
                });
                mGridHeros.setAdapter(adapter);

                mBtnSave.setText("提交");
                mBtnSave.setEnabled(true);
                mBtnSave.setOnClickListener(v -> {
                    if (mSelected.size() != 3) LUtils.toast("必须选择3个");
                    else getPresenter().pick(mSelected);
                });
            }
        }

        // 比赛处于ban阶段
        if (battle.getBattle_status() == 3) {

            if (battle.getBattle_status_user() == 8 && battle.getUser_type() == 1 || battle.getBattle_status_user() == 9 && battle.getUser_type() == 2) {
                mSection.setTitle(R.string.text_ban_title)
                        .setDesc(R.string.text_baned_desc);
                mGridHeros.setVisibility(View.GONE);
                mBtnSave.setText("已提交");
                mBtnSave.setEnabled(false);
            } else {
                mGridHeros.setVisibility(View.VISIBLE);
                mSection.setTitle(R.string.text_ban_title)
                        .setNotice(R.string.text_ban_notice)
                        .setDesc(R.string.text_ban_desc);

                List<Hero> list = new ArrayList<>();
                for (int i=1; i<(battle.getBattle_mode().equals("BO4") ? 5 : 4); i++) {
                    Hero hero = new Hero();
                    try {
                        Method method = battle.getClass().getMethod("get" + (battle.getUser_type() == 2 ? "A" : "B") + "_car" + i);
                        hero.setIndex(Integer.valueOf((String) method.invoke(battle)));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    list.add(hero);
                }

                BanPickAdapter adapter = new BanPickAdapter(this, list, BanPickAdapter.MODE_BAN);
                adapter.setOnItemClickListener(index -> {
                    if (mSelected.contains(index)) {
                        mSelected.remove(index);
                        adapter.select(index);
                    } else {
                        if (mSelected.size() >= 1) {
                            LUtils.toast("只能Ban一个");
                        } else {
                            mSelected.add(index);
                            adapter.select(index);
                        }
                    }
                });

                mGridHeros.removeAllViews();
                mGridHeros.setAdapter(adapter);
                mBtnSave.setText("提交");
                mBtnSave.setEnabled(true);
                mBtnSave.setOnClickListener(v -> {
                    if (mSelected.size() != 1) LUtils.toast("必须Ban一个英雄");
                    else getPresenter().ban(mSelected.get(0).getIndex());
                });
            }
        }

        // 进行中
        if (battle.getBattle_status() == 4) {
            if (battle.getBattle_status_user() == 11  && battle.getUser_type() == 1 || battle.getBattle_status_user() == 12 && battle.getUser_type() == 2) {
                mBtnSave.setText("已提交");
                mBtnSave.setEnabled(false);
                mGridHeros.setVisibility(View.GONE);
                mSection.setTitle("提交比赛结果")
                        .setDesc(String.format(getString(R.string.text_submit_result_desc), battle.getBattle_times(), battle.getWinner_id() == battle.getA_user_id() ? battle.getA_username() : (battle.getWinner_id() == battle.getB_user_id() ? battle.getB_username() : "无结果")));
            } else {
                mSection.setVisibility(View.GONE);
                mGridHeros.setVisibility(View.GONE);
                mBtnSave.setVisibility(View.GONE);
                mLyResult.setVisibility(View.VISIBLE);
                mBtnImWin.setOnClickListener(v -> getPresenter().submit(battle.getUser_type() == 1 ? battle.getA_user_id() : battle.getB_user_id()));
                mBtnImLost.setOnClickListener(v -> getPresenter().submit(battle.getUser_type() == 2 ? battle.getA_user_id() : battle.getB_user_id()));

                if (!battle.getBattle_mode().equals("BO1")) {
                    mLyOngoing.setVisibility(View.VISIBLE);
                    mTvAAccount.setText(battle.getA_gameaccount());
                    mTvBAccount.setText(battle.getB_gameaccount());

                    List<Hero> listA = new ArrayList<>();
                    List<Hero> listB = new ArrayList<>();
                    for (int i=1; i<(battle.getBattle_mode().equals("BO4") ? 5 : 4); i++) {
                        Hero heroA = new Hero();
                        Hero heroB = new Hero();
                        try {
                            String carA = (String) battle.getClass().getMethod("getA_" + "car" + i).invoke(battle);
                            heroA.setIndex(Integer.valueOf(carA));
                            heroA.setBan(carA.equals(battle.getA_ban()));

                            String carB = (String) battle.getClass().getMethod("getB_" + "car" + i).invoke(battle);
                            heroB.setIndex(Integer.valueOf(carB));
                            heroB.setBan(carB.equals(battle.getB_ban()));
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        listA.add(heroA);
                        listB.add(heroB);
                    }

                    BanPickAdapter adapterA = new BanPickAdapter(this, listA, BanPickAdapter.MODE_SHOW);
                    mGridABans.setAdapter(adapterA);

                    BanPickAdapter adapterB = new BanPickAdapter(this, listB, BanPickAdapter.MODE_SHOW);
                    mGridBBans.setAdapter(adapterB);
                }

            }
        }

        // 争议
        if (battle.getBattle_status() == 6) {
            mSection.setVisibility(View.VISIBLE);
            mGridHeros.setVisibility(View.GONE);
            mSection.setTitle("提交比赛结果").setDesc(R.string.text_controversial_desc);
            mBtnSave.setText("重新提交");
            mBtnSave.setOnClickListener(v -> getPresenter().resubmit());
        }

    }

    private void setSimpleDesc() {
        mSection.setVisibility(View.VISIBLE);
        mLyOperation.setVisibility(View.GONE);
        mLyOngoing.setVisibility(View.GONE);
        mGridHeros.setVisibility(View.GONE);
    }

}
