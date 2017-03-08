package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.module.news.NewsDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MatchRulesPresenter.class)
public class MatchRulesFragment extends BaseDataFragment<MatchRulesPresenter, Match> {

    @Bind(R.id.tv_rule_mode_final)
    TextView mTvFinal;

    @Bind(R.id.tv_rule_mode_semifinal)
    TextView mTvSemifinal;

    @Bind(R.id.tv_rule_mode_battle)
    TextView mTvBattle;

    @Bind(R.id.tv_rule_mode)
    TextView mTvMode;

    @Bind(R.id.tv_rule_content)
    TextView mTvContent;

    @Bind(R.id.tv_rule_general)
    TextView mTvGeneral;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment_rules, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setData(Match match) {
        mTvFinal.setText(match.getFinal_battle_mode());
        mTvSemifinal.setText(match.getSemifinal_battle_mode());
        mTvBattle.setText(match.getBattle_mode());
        mTvMode.setText(match.getPattern());
        mTvContent.setText(match.getRule());
        mTvGeneral.setOnClickListener(v -> NewsDetailPresenter.start(getActivity(), match.getArticle_id()));
    }
}