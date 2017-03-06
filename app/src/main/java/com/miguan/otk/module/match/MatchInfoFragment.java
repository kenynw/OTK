package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView mTvDesc;

    @Bind(R.id.tv_match_enroll)
    TextView mTvEnroll;

    @Bind(R.id.tv_match_bench)
    TextView mTvBench;

    @Bind(R.id.tv_match_qq_group)
    TextView mTvGroup;

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
//        mTvDesc.loadData(match.getContent(), "text/html;charset=utf-8", null);
        mTvDesc.setText(Html.fromHtml(match.getContent()));

        mTvEnroll.setText(String.format(getString(R.string.btn_enroll_list), match.getCount_competitor(), match.getCompetitors()));
        mTvEnroll.setOnClickListener(v -> getPresenter().toUserList(match.getCompetition_id(), 1));
        mTvBench.setText(String.format(getString(R.string.btn_bench_list), match.getCount_sub_competitor(), match.getSubstitute_competitors()));
        mTvBench.setOnClickListener(v -> getPresenter().toUserList(match.getCompetition_id(), 2));
        mTvGroup.setOnClickListener(v -> getPresenter().toQQGroup());

        // 设置奖金
        mTvReward.setText(getReward(match.getReward_money(), match.getReward_sb()));
        mTvRewardWinner.setText(getReward(match.getReward_1(), match.getSb_reward_1()));
        mTvReward2.setText(getReward(match.getReward_2(), match.getSb_reward_2()));
        mTvReward3_4.setText(getReward(match.getReward_3_4(), match.getSb_reward_3_4()));
        mTvReward5_8.setText(getReward(match.getReward_5_8(), match.getSb_reward_5_8()));
        mTvReward9_16.setText(getReward(match.getReward_9_16(), match.getSb_reward_9_16()));
        mTvReward17_32.setText(getReward(match.getReward_17_32(), match.getSb_reward_17_32()));
        mTvReward33_64.setText(getReward(match.getReward_33_64(), match.getSb_reward_33_64()));
    }

    private String getReward(long money, long sb) {
        if (money > 0 && sb > 0) return money + "元宝" + "&" + sb + "撒币";
        else if (money > 0) return money + "元宝";
        else if (sb > 0) return sb + "撒币";
        return "0";
    }

}
