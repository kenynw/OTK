package com.miguan.otk.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/2/4. LiaoPeiKun Inc. All rights reserved.
 */

public class SectionView extends LinearLayout {

    @Bind(R.id.tv_battle_status_title)
    TextView mTvTitle;

    @Bind(R.id.tv_battle_status_notice)
    TextView mTvNotice;

    @Bind(R.id.tv_battle_status_desc)
    TextView mTvDesc;

    public SectionView(Context context) {
        this(context, null);
    }

    public SectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) return;
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        View view = LayoutInflater.from(context).inflate(R.layout.include_battle_status, this);
        ButterKnife.bind(this, view);
    }

    public SectionView setTitle(@StringRes int resID) {
        return setTitle(getContext().getString(resID));
    }

    public SectionView setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    public SectionView setNotice(@StringRes int resID) {
        if (resID > 0) {
            setNotice(getContext().getString(resID));
        }
        return this;
    }

    public SectionView setNotice(String notice) {
        if (!TextUtils.isEmpty(notice)) {
            mTvNotice.setText(notice);
            mTvNotice.setVisibility(VISIBLE);
        }
        return this;
    }

    public SectionView setDesc(@StringRes int resID) {
        return setDesc(getContext().getString(resID));
    }

    public SectionView setDesc(String desc) {
        mTvDesc.setText(desc);
        return this;
    }

}
