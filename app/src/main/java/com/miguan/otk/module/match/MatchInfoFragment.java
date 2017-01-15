package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView mTvDesc;

    @Bind(R.id.btn_match_enroll)
    Button mBtnEnroll;

    @Bind(R.id.btn_match_bench)
    Button mBtnBench;

    @Bind(R.id.btn_match_qq_group)
    Button mBtnGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setData(Match match) {
        mTvDesc.setText(Html.fromHtml(match.getContent()));
        mBtnEnroll.setText(String.format(getString(R.string.btn_enroll_list), match.getCount_competitor(), match.getCompetitors()));
        mBtnEnroll.setOnClickListener(v -> getPresenter().toUserList(match.getCompetition_id(), 1));
        mBtnBench.setText(String.format(getString(R.string.btn_bench_list), match.getCount_sub_competitor(), match.getSubstitute_competitors()));
        mBtnBench.setOnClickListener(v -> getPresenter().toUserList(match.getCompetition_id(), 2));
        mBtnGroup.setOnClickListener(v -> getPresenter().toQQGroup(match.getQq_group_url()));
    }

}
