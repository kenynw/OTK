package com.miguan.otk.module.battle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.adapter.BanPickAdapter;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.module.match.SubmitShotActivity;
import com.sgun.utils.LUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(BattlingPresenter.class)
public class BattlingFragment extends ChainFragment<BattlingPresenter> {

    @Bind(R.id.btn_battling_judge)
    Button mBtnContact;

    @Bind(R.id.btn_battling_screenshot)
    Button mBtnScreenshot;

    @Bind(R.id.btn_battling_chatroom)
    Button mBtnChatroom;

    @Bind(R.id.tv_battle_status_title)
    TextView mTvTitle;

    @Bind(R.id.tv_battle_status_notice)
    TextView mTvNotice;

    @Bind(R.id.tv_battle_status_desc)
    TextView mTvDesc;

    @Bind(R.id.rcv_battling_list)
    ExGridView mGvHeros;

    @Bind(R.id.btn_battling_save)
    Button mBtnSave;

    private List<Integer> mSelected = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battle_fragment_battling, container, false);
        ButterKnife.bind(this, view);

        mBtnContact.setOnClickListener(v -> startActivity(new Intent(getActivity(), ContactJudgeActivity.class)));
        mBtnScreenshot.setOnClickListener(v -> startActivity(new Intent(getActivity(), SubmitShotActivity.class)));
//        mBtnChatroom.setOnClickListener(v -> startActivity(new Intent()));

        return view;
    }

    public void setData(Battle battle) {
        // 比赛处于pick阶段
        if (battle.getBattle_status() == 2) {
            if (battle.getBattle_status_user() == 5 && battle.getUser_type() == 1 || battle.getBattle_status_user() == 6 && battle.getUser_type() == 2) {
                setPicked();
            } else {
                mTvTitle.setText(R.string.text_pick_title);
                mTvNotice.setText(R.string.text_pick_notice);
                mTvDesc.setText(R.string.text_pick_desc);

                BanPickAdapter adapter = new BanPickAdapter(getActivity(), BanPickAdapter.MODE_PICK);
                adapter.setOnItemClickListener(index -> {
                    if (mSelected.contains(index)) {
                        mSelected.remove(index);
                        adapter.select(index);
                    } else {
                        if ((battle.getBattle_mode().equals("BO3") && mSelected.size() >= 3)
                                || (battle.getBattle_mode().equals("BO5") && mSelected.size() >= 4)) {
                            LUtils.toast("已达最大数量");
                        } else {
                            mSelected.add(index);
                            adapter.select(index);
                        }
                    }
                    LUtils.log("hero index: " + index + "hero name: ");
                });
                mGvHeros.setAdapter(adapter);
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
                mTvTitle.setText(R.string.text_ban_title);
                mTvNotice.setText(R.string.text_ban_notice);
                mTvDesc.setText(R.string.text_ban_desc);

                List<Integer> list = new ArrayList<>();
                if (battle.getUser_type() == 2) {
                    list.add(Integer.valueOf(battle.getA_car1()));
                    list.add(Integer.valueOf(battle.getA_car2()));
                    list.add(Integer.valueOf(battle.getA_car3()));
                    if (battle.getBattle_mode().equals("BO4")) {
                        list.add(Integer.valueOf(battle.getA_car4()));
                    }
                } else if (battle.getUser_type() == 1) {
                    list.add(Integer.valueOf(battle.getB_car1()));
                    list.add(Integer.valueOf(battle.getB_car2()));
                    list.add(Integer.valueOf(battle.getB_car3()));
                    if (battle.getBattle_mode().equals("BO4")) {
                        list.add(Integer.valueOf(battle.getB_car4()));
                    }
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

                mGvHeros.setAdapter(adapter);
                mBtnSave.setOnClickListener(v -> {
                    if (mSelected.size() != 1) LUtils.toast("必须Ban一个英雄");
                    else getPresenter().ban(mSelected.get(0));
                });
            }
        }
    }

    public void setPicked() {
        mTvTitle.setText(R.string.text_pick_title);
        mTvNotice.setVisibility(View.GONE);
        mTvDesc.setText(R.string.text_picked_desc);
        mBtnSave.setText("已提交");
    }

    public void setBaned() {
        mTvTitle.setText(R.string.text_ban_title);
        mTvNotice.setVisibility(View.GONE);
        mTvDesc.setText(R.string.text_baned_desc);
        mBtnSave.setText("已提交");
    }
}
