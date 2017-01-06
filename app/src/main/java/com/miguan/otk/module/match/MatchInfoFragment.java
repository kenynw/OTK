package com.miguan.otk.module.match;

import android.content.Intent;
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
import com.miguan.otk.module.against.AgainstActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MatchInfoPresenter.class)
public class MatchInfoFragment extends BaseDataFragment<MatchInfoPresenter, Match> {

    @Bind(R.id.tv_match_enroll)
    TextView mTvEnroll;

    @Bind(R.id.tv_match_bench)
    TextView mTvBench;

    @Bind(R.id.btn_match_detail_enroll)
    TextView mBtnEnroll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment_info, container, false);
        ButterKnife.bind(this, view);

        mTvEnroll.setOnClickListener(v -> getPresenter().showEnrollList(0));
        mTvBench.setOnClickListener(v -> getPresenter().showEnrollList(1));
        mBtnEnroll.setOnClickListener(v -> startActivity(new Intent(getActivity(), AgainstActivity.class)));

        return view;
    }
}
