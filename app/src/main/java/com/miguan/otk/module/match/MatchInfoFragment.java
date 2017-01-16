package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MatchInfoPresenter.class)
public class MatchInfoFragment extends BaseDataFragment<MatchInfoPresenter, Match> {

    @Bind(R.id.tv_match_desc)
    WebView mTvDesc;

    @Bind(R.id.btn_match_enroll)
    Button mBtnEnroll;

    @Bind(R.id.btn_match_bench)
    Button mBtnBench;

    @Bind(R.id.btn_match_qq_group)
    Button mBtnGroup;

    @Bind(R.id.tv_reward)
    TextView mTvReward;

    @Bind(R.id.tv_reward_winner)
    TextView mTvRewardWinner;

    @Bind(R.id.tv_reward_2)
    TextView mTvReward2;

    @Bind(R.id.tv_reward_3_4)
    TextView mTvReward3_4;

    @Bind(R.id.tv_reward_5_8)
    TextView mTvReward5_8;

    @Bind(R.id.tv_reward_9_16)
    TextView mTvReward9_16;

    @Bind(R.id.tv_reward_17_32)
    TextView mTvReward17_32;

    @Bind(R.id.tv_reward_33_64)
    TextView mTvReward33_64;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setData(Match match) {
        mTvDesc.loadData(match.getContent(), "text/html;charset=utf-8", null);

        mBtnEnroll.setText(String.format(getString(R.string.btn_enroll_list), match.getCount_competitor(), match.getCompetitors()));
        mBtnEnroll.setOnClickListener(v -> getPresenter().toUserList(match.getCompetition_id(), 1));
        mBtnBench.setText(String.format(getString(R.string.btn_bench_list), match.getCount_sub_competitor(), match.getSubstitute_competitors()));
        mBtnBench.setOnClickListener(v -> getPresenter().toUserList(match.getCompetition_id(), 2));
        mBtnGroup.setOnClickListener(v -> getPresenter().toQQGroup(match.getQq_group_url()));

        // 设置奖金 操蛋又冗余的代码
        mTvReward.setText(String.format("￥%s", match.getReward_money()>0 ? match.getReward_money() : match.getReward_sb()));
        mTvRewardWinner.setText(String.format("￥%s", match.getReward_1()>0 ? match.getReward_1() : match.getSb_reward_2()));
        mTvReward2.setText(String.format("￥%s", match.getReward_2()>0 ? match.getReward_2() : match.getSb_reward_2()));
        mTvReward3_4.setText(String.format("￥%s", match.getReward_3_4()>0 ? match.getReward_3_4() : match.getSb_reward_3_4()));
        mTvReward5_8.setText(String.format("￥%s", match.getReward_5_8()>0 ? match.getReward_5_8() : match.getSb_reward_5_8()));
        mTvReward9_16.setText(String.format("￥%s", match.getReward_9_16()>0 ? match.getReward_9_16() : match.getSb_reward_9_16()));
        mTvReward17_32.setText(String.format("￥%s", match.getReward_17_32()>0 ? match.getReward_17_32() : match.getSb_reward_17_32()));
        mTvReward33_64.setText(String.format("￥%s", match.getReward_33_64()>0 ? match.getReward_33_64() : match.getSb_reward_33_64()));
    }

}
