package com.miguan.otk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/22. LiaoPeiKun Inc. All rights reserved.
 */

public class BanPickView extends ExGridView {

    private int mColumnCount;

    private float mVerticalDivider;

    private float mHorizontalDivider;

    private int mMode;

    public BanPickView(Context context) {
        this(context, null, 0);
    }

    public BanPickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BanPickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initial(attrs);
    }

    private void initial(AttributeSet attrs) {
        String[] pickList = getContext().getResources().getStringArray(R.array.items_pick_list);


        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BanPickView);
        try {
            mColumnCount = ta.getInteger(R.styleable.BanPickView_columnCount, 5);
            mHorizontalDivider = ta.getDimension(R.styleable.BanPickView_horizontalDivider, LUtils.dp2px(8));
            mVerticalDivider = ta.getDimension(R.styleable.BanPickView_verticalDivider, LUtils.dp2px(8));
            mMode = ta.getInt(R.styleable.BanPickView_mode, 1);
        } finally {
            ta.recycle();
        }
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private RecyclerView.Adapter mAdapter;

        private Context mContext;

        public Adapter(RecyclerView.Adapter adapter, Context context) {
            mAdapter = adapter;
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.iv_hero_thumb)
            ImageView mIvThumb;

            @Bind(R.id.iv_hero_status)
            CheckBox mCbName;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);


            }
        }

    }

}
