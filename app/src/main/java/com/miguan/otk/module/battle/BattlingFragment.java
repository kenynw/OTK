package com.miguan.otk.module.battle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.adapter.BanPickAdapter;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Hero;
import com.miguan.otk.widget.SectionView;
import com.sgun.utils.LUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(BattlingPresenter.class)
public class BattlingFragment extends ChainFragment<BattlingPresenter> {

    @Bind(R.id.tv_battling_opponent)
    TextView mTvOpponent;

    @Bind(R.id.btn_pick_copy)
    Button mBtnCopy;

    @Bind(R.id.btn_battling_judge)
    Button mBtnContact;

    @Bind(R.id.btn_battling_screenshot)
    Button mBtnScreenshot;

    @Bind(R.id.btn_battling_chatroom)
    Button mBtnChatroom;

    @Bind(R.id.section_battling_status)
    SectionView mSectionStatus;

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

    @Bind(R.id.btn_battling_save)
    Button mBtnSave;

    private List<Hero> mSelected = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battle_fragment_battling, container, false);
        ButterKnife.bind(this, view);

        mBtnCopy.setOnClickListener(v -> getPresenter().copyName(mTvOpponent.getText().toString().trim()));
        mBtnContact.setOnClickListener(v -> startActivity(new Intent(getActivity(), ContactJudgeActivity.class)));
        mBtnScreenshot.setOnClickListener(v -> getPresenter().toShot());
//        mBtnChatroom.setOnClickListener(v -> startActivity(new Intent()));

        return view;
    }

    public void setData(Battle battle) {
        // 准备
        if (battle.getBattle_status() == 1) {
            if ((battle.getUser_type() == 1 && battle.getBattle_status_user() == 2) || (battle.getUser_type() == 2 && battle.getBattle_status_user() == 3)) {
                mBtnSave.setText("已准备");
                mBtnSave.setEnabled(false);
            } else {
                mBtnSave.setText("准备");
                mBtnSave.setOnClickListener(v -> getPresenter().ready(battle.getBattle_id()));
            }
        }

        // 比赛处于pick阶段
        if (battle.getBattle_status() == 2) {
            if (battle.getBattle_status_user() == 5 && battle.getUser_type() == 1 || battle.getBattle_status_user() == 6 && battle.getUser_type() == 2) {
                setPicked();
            } else {
                mSectionStatus.setTitle(R.string.text_pick_title)
                        .setNotice(R.string.text_pick_notice)
                        .setDesc(R.string.text_pick_desc);

                BanPickAdapter adapter = new BanPickAdapter(getActivity(), BanPickAdapter.MODE_PICK);
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
                mBtnSave.setOnClickListener(v -> {
                    if (mSelected.size() != 3) LUtils.toast("必须选择3个");
                    else getPresenter().pick(mSelected);
                });
            }
        }

        // 比赛处于ban阶段
        if (battle.getBattle_status() == 3) {
            if (battle.getBattle_status_user() == 8 && battle.getUser_type() == 1 || battle.getBattle_status_user() == 9 && battle.getUser_type() == 2) {
                setBaned();
            } else {
                mSectionStatus.setTitle(R.string.text_ban_title)
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

                BanPickAdapter adapter = new BanPickAdapter(getActivity(), list, BanPickAdapter.MODE_BAN);
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
                    LUtils.log("hero index: " + index + "hero name: ");
                });

                mGridHeros.setAdapter(adapter);
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
                mSectionStatus.setTitle("提交比赛结果")
                        .setDesc(String.format(getString(R.string.text_submit_result_desc), battle.getBattle_times(), battle.getWinner_id() == battle.getA_user_id() ? battle.getA_username() : (battle.getWinner_id() == battle.getB_user_id() ? battle.getB_username() : "无结果")));
            } else {
                mSectionStatus.setVisibility(View.GONE);
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

                    BanPickAdapter adapterA = new BanPickAdapter(getActivity(), listA, BanPickAdapter.MODE_SHOW);
                    mGridABans.setAdapter(adapterA);

                    BanPickAdapter adapterB = new BanPickAdapter(getActivity(), listB, BanPickAdapter.MODE_SHOW);
                    mGridBBans.setAdapter(adapterB);
                }

            }
        }

        // 结束
        if (battle.getBattle_status() == 5) {
            mTvTitle.setText(R.string.text_battle_ended);
            mTvDesc.setText(String.format(getString(R.string.text_battle_ended_desc), battle.getWinner_id() == battle.getA_user_id() ? battle.getA_username() : (battle.getWinner_id() == battle.getB_user_id() ? battle.getB_username() : "无结果")));

            mBtnSave.setText("结束");
            if (battle.getIs_end()) mBtnSave.setVisibility(View.GONE);
            else mBtnSave.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), BattleActivity.class);
                intent.putExtra("battle_id", battle.getBattle_id());
                startActivity(intent);
            });
        }

        // 争议
        if (battle.getBattle_status() == 6) {
            mSectionStatus.setVisibility(View.VISIBLE);
            mGridHeros.setVisibility(View.GONE);
            mSectionStatus.setTitle("提交比赛结果").setDesc(R.string.text_controversial_desc);
            mBtnSave.setText("重新提交");
            mBtnSave.setOnClickListener(v -> getPresenter().resubmit());
        }

    }

    public void setPicked() {
        mSectionStatus.setTitle(R.string.text_pick_title).setDesc(R.string.text_picked_desc);
        mBtnSave.setText("已提交");
        mBtnSave.setEnabled(false);
    }

    public void setBaned() {
        mSectionStatus.setTitle(R.string.text_ban_title).setDesc(R.string.text_baned_desc);
        mBtnSave.setText("已提交");
        mBtnSave.setEnabled(false);
    }
}
