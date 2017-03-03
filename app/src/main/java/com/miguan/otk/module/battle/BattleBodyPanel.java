package com.miguan.otk.module.battle;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.adapter.BanPickAdapter;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Hero;
import com.miguan.otk.utils.LUtils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/2/28. LiaoPeiKun Inc. All rights reserved.
 */

public class BattleBodyPanel {

    @Bind(R.id.layout_battle_status)
    LinearLayout mLyStatus;

    @Bind(R.id.tv_battle_a_status)
    TextView mTvAStatus;

    @Bind(R.id.tv_battle_b_status)
    TextView mTvBStatus;

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

    @Bind(R.id.layout_battle_desc)
    LinearLayout mLyDesc;

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

    @Bind(R.id.btn_battle_status_save)
    Button mBtnSave;

    @Bind(R.id.ly_battling_submit_result)
    LinearLayout mLyResult;

    @Bind(R.id.btn_battling_im_win)
    Button mBtnImWin;

    @Bind(R.id.btn_battling_im_lost)
    Button mBtnImLost;

    private List<Hero> mSelected = new ArrayList<>();

    private Activity mActivity;

    private Battle mBattle;

    private BattleProxy mProxy;

    public BattleBodyPanel(Activity activity, View view, Battle battle, BattleProxy proxy) {
        mActivity = activity;
        mProxy = proxy;

        ButterKnife.bind(this, view);

        load(battle);
    }

    public void load(Battle battle) {
        if (mBattle != null && mBattle.getBattle_status_user() == battle.getBattle_status_user()) {
            return;
        }
        mBattle = battle;

        hideAllLayout();
        if (battle.getUser_type() == 0 || battle.getUser_type() == 3) { // 观战模式 用户类型为游客或未参赛
            setDescText("比赛阶段", String.format("%s阶段比赛正在进行中", battle.getBattle_times()), "");
        } else if (battle.getBattle_status() == 0) { // 等待对手生成
            setDescText("等待对手生成", "您的对手还没有结束当前比赛，请耐心等待", "");
        } else if (battle.getBattle_status() == 1) { // 准备阶段
            setUserStatus();
            setDescText("准备阶段", "点击“准备”按钮进入比赛，准备截断结束之后必须互相添加对手，同事在网页聊天室、游戏里说明是否进行卡组选择界面的截图", "");
            mBtnSave.setVisibility(View.VISIBLE);
            if ((battle.getUser_type() == 1 && battle.getBattle_status_user() == 2) || (battle.getUser_type() == 2 && battle.getBattle_status_user() == 3)) {
                mBtnSave.setText("已准备");
                mBtnSave.setEnabled(false);
            } else {
                mBtnSave.setText("准备");
                mBtnSave.setOnClickListener(v -> mProxy.ready());
            }
        } else if (battle.getBattle_status() == 2) { // 比赛处于pick阶段
            setOperation();
            mSelected.clear();
            if (battle.getBattle_status_user() == 5 && battle.getUser_type() == 1
                    || battle.getBattle_status_user() == 6 && battle.getUser_type() == 2) {
                setDescText(R.string.text_battle_pick_title, R.string.text_battle_picked_desc, 0);
                setButtonDisable();
            } else {
                setDescText(
                        R.string.text_battle_pick_title,
                        R.string.text_battle_pick_desc,
                        R.string.text_battle_pick_notice
                );
                setPick();
            }
        } else if (battle.getBattle_status() == 3) { // 比赛处于ban阶段
            setOperation();
            mSelected.clear();
            if (battle.getBattle_status_user() == 8 && battle.getUser_type() == 1 || battle.getBattle_status_user() == 9 && battle.getUser_type() == 2) {
                setDescText(R.string.text_battle_ban_title, R.string.text_battle_baned_desc, 0);
                setButtonDisable();
            } else {
                setDescText(
                        R.string.text_battle_ban_title,
                        R.string.text_battle_ban_desc,
                        R.string.text_battle_ban_notice
                );

                setBan();
            }
        } else if (battle.getBattle_status() == 4) { // 进行中
            setOperation();
            mSelected.clear();
            if (battle.getBattle_status_user() == 11 && battle.getUser_type() == 1 || battle.getBattle_status_user() == 12 && battle.getUser_type() == 2) {
                mLyDesc.setVisibility(View.VISIBLE);
                mTvTitle.setText(R.string.text_battle_submit_result);
                mTvDesc.setText(String.format(mActivity.getString(R.string.text_battle_submit_result_desc), battle.getBattle_times(), battle.getWinner_id() == battle.getA_user_id() ? battle.getA_username() : (battle.getWinner_id() == battle.getB_user_id() ? battle.getB_username() : "无结果")));
                setButtonDisable();
            } else {
                setBPList();
                setSubmitButton();
            }
        } else if (battle.getBattle_status() == 5) { // 结束
            setUserStatus();
            setDescText("比赛结束", String.format(mActivity.getString(R.string.text_battle_ended_desc), battle.getWinner_id() == battle.getA_user_id() ? battle.getA_username() : (battle.getWinner_id() == battle.getB_user_id() ? battle.getB_username() : "无结果")), "");

            if (battle.getWinner_id() == 1 && battle.getNext_battle_id() > 0) {
                mBtnSave.setText("进入下一轮");
                mBtnSave.setOnClickListener(v -> EventBus.getDefault().post(battle));
            } else if (battle.getIs_end()) {
                mBtnSave.setVisibility(View.GONE);
            } else {
                mBtnSave.setText("结束");
                mBtnSave.setOnClickListener(v -> {
                    Intent intent = new Intent(mActivity, BattleActivity.class);
                    intent.putExtra("battle_id", battle.getBattle_id());
                    mActivity.startActivity(intent);
                });
            }
        } else if (battle.getBattle_status() == 6) { // 争议
            setOperation();
            setDescText(R.string.text_battle_submit_result, R.string.text_battle_controversial_desc, 0);

            mBtnSave.setVisibility(View.VISIBLE);
            mBtnSave.setText("重新提交");
            mBtnSave.setOnClickListener(v -> mProxy.cancelResult());
        }
    }

    // 设置用户状态
    private void setUserStatus() {
        mLyStatus.setVisibility(View.VISIBLE);
        mTvAStatus.setText(getUserStatusText(mBattle.getA_status()));
        mTvBStatus.setText(getUserStatusText(mBattle.getB_status()));
    }

    private void setOperation() {
        mLyOperation.setVisibility(View.VISIBLE);
        mTvOpponent.setText(mBattle.getUser_type() == 1 ? mBattle.getB_gameaccount() : mBattle.getA_gameaccount());
        mBtnCopy.setOnClickListener(v -> copyName(mTvOpponent.getText().toString().trim()));
        mTvContact.setOnClickListener(v -> mActivity.startActivity(new Intent(mActivity, ContactJudgeActivity.class)));
        mTvScreenshot.setOnClickListener(v -> toShot(mBattle));
        mTvChatroom.setOnClickListener(v -> {
            String qq = mBattle.getUser_type() == 1 ? mBattle.getB_qq() : mBattle.getA_qq();
            if (TextUtils.isEmpty(qq)) {
                LUtils.toast("对方未设置QQ");
            } else {
                Intent intent = new Intent();
                intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qq));
                try {
                    mActivity.startActivity(intent);
                } catch (Exception e) {
                    LUtils.toast("未安装手Q或安装的版本不支持");
                }
            }
        });
    }

    // 显示Pick界面
    private void setPick() {
        mGridHeros.setVisibility(View.VISIBLE);
        mBtnSave.setVisibility(View.VISIBLE);

        BanPickAdapter adapter = new BanPickAdapter(mActivity, BanPickAdapter.MODE_PICK);
        adapter.setOnItemClickListener(hero -> {
            if (mSelected.contains(hero)) {
                mSelected.remove(hero);
                adapter.select(hero);
            } else {
                if ((mBattle.getBattle_mode().equals("BO3") && mSelected.size() >= 3)
                        || (mBattle.getBattle_mode().equals("BO5") && mSelected.size() >= 4)) {
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
            int count = mBattle.getBattle_mode().equals("BO5") ? 4 : 3;
            if (mSelected.size() != count) LUtils.toast("必须选择" + count + "个");
            else mProxy.pick(mSelected);
        });
    }

    // 设置Ban界面
    private void setBan() {
        mGridHeros.setVisibility(View.VISIBLE);
        mBtnSave.setVisibility(View.VISIBLE);

        List<Hero> list = new ArrayList<>();
        for (int i = 1; i < (mBattle.getBattle_mode().equals("BO5") ? 5 : 4); i++) {
            Hero hero = new Hero();
            try {
                Method method = mBattle.getClass().getMethod("get" + (mBattle.getUser_type() == 2 ? "A" : "B") + "_car" + i);
                hero.setIndex(Integer.valueOf((String) method.invoke(mBattle)));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            list.add(hero);
        }

        BanPickAdapter adapter = new BanPickAdapter(mActivity, list, BanPickAdapter.MODE_BAN);
        adapter.setOnItemClickListener(hero -> {
            if (mSelected.contains(hero)) {
                mSelected.remove(hero);
                adapter.select(hero);
            } else {
                if (mSelected.size() >= 1) {
                    LUtils.toast("只能Ban一个");
                } else {
                    mSelected.add(hero);
                    adapter.select(hero);
                }
            }
        });

        mGridHeros.removeAllViews();
        mGridHeros.setAdapter(adapter);
        mBtnSave.setText("提交");
        mBtnSave.setEnabled(true);
        mBtnSave.setOnClickListener(v -> {
            if (mSelected.size() != 1) LUtils.toast("必须Ban一个英雄");
            else mProxy.ban(mSelected.get(0).getIndex());
        });
    }

    // 设置按钮不可用
    private void setButtonDisable() {
        mBtnSave.setVisibility(View.VISIBLE);
        mBtnSave.setText("已提交");
        mBtnSave.setEnabled(false);
    }

    // 设置提交结果按钮
    private void setSubmitButton() {
        mBtnSave.setVisibility(View.GONE);
        mLyResult.setVisibility(View.VISIBLE);
        mBtnImWin.setOnClickListener(v -> mProxy.submitResult(mBattle.getUser_type() == 1 ? mBattle.getA_user_id() : mBattle.getB_user_id()));
        mBtnImLost.setOnClickListener(v -> mProxy.submitResult(mBattle.getUser_type() == 2 ? mBattle.getA_user_id() : mBattle.getB_user_id()));
    }

    // 显示两个用户BP列表
    private void setBPList() {
        mLyOngoing.setVisibility(View.VISIBLE);

        if (!mBattle.getBattle_mode().equals("BO1")) {
            mTvAAccount.setText(mBattle.getA_gameaccount());
            mTvBAccount.setText(mBattle.getB_gameaccount());

            List<Hero> listA = new ArrayList<>();
            List<Hero> listB = new ArrayList<>();
            for (int i = 1; i < (mBattle.getBattle_mode().equals("BO5") ? 5 : 4); i++) {
                Hero heroA = new Hero();
                Hero heroB = new Hero();
                try {
                    String carA = (String) mBattle.getClass().getMethod("getA_" + "car" + i).invoke(mBattle);
                    heroA.setIndex(Integer.valueOf(carA));
                    heroA.setBan(carA.equals(mBattle.getA_ban()));

                    String carB = (String) mBattle.getClass().getMethod("getB_" + "car" + i).invoke(mBattle);
                    heroB.setIndex(Integer.valueOf(carB));
                    heroB.setBan(carB.equals(mBattle.getB_ban()));
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

            BanPickAdapter adapterA = new BanPickAdapter(mActivity, listA, BanPickAdapter.MODE_SHOW);
            mGridABans.removeAllViews();
            mGridABans.setAdapter(adapterA);

            BanPickAdapter adapterB = new BanPickAdapter(mActivity, listB, BanPickAdapter.MODE_SHOW);
            mGridBBans.removeAllViews();
            mGridBBans.setAdapter(adapterB);
        }

    }

    // 设置描述文本
    private void setDescText(int titleRes, int descRes, int noticeRes) {
        mLyDesc.setVisibility(View.VISIBLE);
        mTvTitle.setText(mActivity.getString(titleRes));
        mTvDesc.setText(mActivity.getString(descRes));
        if (noticeRes > 0) {
            mTvNotice.setVisibility(View.VISIBLE);
            mTvNotice.setText(mActivity.getString(noticeRes));
        } else {
            mTvNotice.setVisibility(View.GONE);
        }
    }

    // 设置描述文本
    private void setDescText(String title, String desc, String notice) {
        mLyDesc.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
        mTvDesc.setText(desc);
        if (TextUtils.isEmpty(notice)) {
            mTvNotice.setVisibility(View.GONE);
        } else {
            mTvNotice.setVisibility(View.VISIBLE);
            mTvNotice.setText(notice);
        }
    }

    // 隐藏所有布局
    private void hideAllLayout() {
        mLyStatus.setVisibility(View.GONE);
        mLyOperation.setVisibility(View.GONE);
        mLyDesc.setVisibility(View.GONE);
        mGridHeros.setVisibility(View.GONE);
        mLyOngoing.setVisibility(View.GONE);
        mLyResult.setVisibility(View.GONE);
        mBtnSave.setVisibility(View.GONE);
    }

    private String getUserStatusText(String userStatus) {
        if (TextUtils.isEmpty(userStatus)) return mBattle.getBattle_status() == 1 ? "未准备" : "失败";
        return userStatus;
    }

    public void copyName(String name) {
        ClipboardManager cm = (ClipboardManager) (mActivity).getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText(null, name));
        LUtils.toast("复制成功");
    }

    public void toShot(Battle battle) {
        Intent intent = new Intent(mActivity, ShotDetailActivity.class);
        intent.putExtra("battle", battle);
        mActivity.startActivity(intent);
    }

}
