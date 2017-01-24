package com.miguan.otk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/22. LiaoPeiKun Inc. All rights reserved.
 */

public class BanPickAdapter extends BaseAdapter {

    public static final int MODE_PICK = 0;

    public static final int MODE_BAN = 1;

    public static final int MODE_SHOW = 3;

    private final int[] HERO_RES = new int[] {
            R.mipmap.ic_warrior, R.mipmap.ic_mage, R.mipmap.ic_preist,
            R.mipmap.ic_rouge, R.mipmap.ic_paladin, R.mipmap.ic_warlock,
            R.mipmap.ic_druid, R.mipmap.ic_hunter, R.mipmap.ic_shaman
    };

    private Context mContext;

    private List<Integer> mHeroList = new ArrayList<>();

    private List<Integer> mSelected = new ArrayList<>();

    private int mMode;

    public BanPickAdapter(Context context, List<Integer> list, int mode) {
        mContext = context;
        mHeroList = list;
        mMode = mode;
    }

    public BanPickAdapter(Context context, int mode) {
        mContext = context;
        for (int i=0; i<HERO_RES.length; i++) {
            mHeroList.add(i);
        }
        mMode = mode;
    }

    public void select(Integer index) {
        if (mSelected.contains(index)) {
            mSelected.remove(index);
        } else {
            mSelected.add(index);
        }
        notifyDataSetChanged();
    }

    public Map<String, String> getSelected() {
//        if (mSelected != null && mSelected.size() > 0) {
//            Map<String, String> map = new HashMap<>();
//            for (int i = 0; i < mSelected.size(); i++) {
//                map.put("car" + (i + 1), mSelected.get(i));
//            }
//            return map;
//        }
        return null;
    }

    @Override
    public int getCount() {
        return mHeroList.size();
    }

    @Override
    public Integer getItem(int position) {
        return mHeroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PickViewHolder holder = new PickViewHolder(parent);
        holder.setData(position);
        return holder.itemView;
    }

    class PickViewHolder extends BaseViewHolder<Integer> {

        @Bind(R.id.iv_hero_thumb)
        ImageView mIvThumb;

        @Bind(R.id.cb_hero_status)
        CheckBox mCbName;

        @Bind(R.id.view_hero_mask)
        View mMask;

        public PickViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_grid_pick);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(Integer hero) {
            mIvThumb.setImageResource(HERO_RES[hero]);
            if (mMode == MODE_PICK) {
                mCbName.setVisibility(View.VISIBLE);
                mCbName.setChecked(mSelected.contains(hero));
                mMask.setVisibility(View.GONE);
            } else if (mMode == MODE_BAN) {
                mCbName.setVisibility(View.GONE);
                mMask.setVisibility(mSelected.contains(hero) ? View.VISIBLE : View.GONE);
            } else {
                mCbName.setVisibility(View.GONE);
                mMask.setVisibility(View.GONE);
            }
            if (mMode != MODE_SHOW) {
                itemView.setOnClickListener(v -> {
                    if (mListener != null) mListener.onItemClick(hero);
                });
            }
        }

    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Integer index);
    }

}
