package com.miguan.otk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.constant.Hero;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.mode;

/**
 * Copyright (c) 2017/1/22. LiaoPeiKun Inc. All rights reserved.
 */

public class BPAdapter extends BaseAdapter {

    public static final int MODE_PICK = 0;

    public static final int MODE_BAN = 1;

    public static final int MODE_SHOW = 3;

    private final int[] HERO_RES = new int[] {
            R.mipmap.ic_warrior, R.mipmap.ic_mage, R.mipmap.ic_preist,
            R.mipmap.ic_rouge, R.mipmap.ic_paladin, R.mipmap.ic_warlock,
            R.mipmap.ic_druid, R.mipmap.ic_hunter, R.mipmap.ic_shaman
    };

    private Context mContext;

    private List<Hero> mHeroList = new ArrayList<>();

    private List<Hero> mSelected = new ArrayList<>();

    private int mMode;

    public BPAdapter(Context context, List<Hero> list, int mode) {
        mContext = context;
        mHeroList = list;
        mMode = mode;
    }

    public BPAdapter(Context context) {
        mContext = context;
        mMode = mode;

        for (Hero hero : Hero.values()) {
            mHeroList.add(hero);
        }

//        for (int i=0; i<HERO_RES.length; i++) {
//            Hero hero = new Hero();
//            hero.setName(context.getResources().getStringArray(R.array.items_pick_list)[i]);
//            hero.setIndex(i + 1);
//            hero.setCheck(false);
//            mHeroList.add(hero);
//        }
    }

    public void select(Hero hero) {
        if (mSelected.contains(hero)) {
            mSelected.remove(hero);
            hero.setCheck(false);
        } else {
            mSelected.add(hero);
            hero.setCheck(true);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mHeroList.size();
    }

    @Override
    public Hero getItem(int position) {
        return mHeroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PickViewHolder holder = new PickViewHolder(parent);
        holder.setData(mHeroList.get(position));
        return holder.itemView;
    }

    class PickViewHolder extends BaseViewHolder<Hero> {

        @Bind(R.id.ly_hero_item)
        FrameLayout mLyItem;

        @Bind(R.id.iv_hero_thumb)
        ImageView mIvThumb;

        @Bind(R.id.iv_hero_status)
        ImageView mIvStatus;

        @Bind(R.id.tv_hero_mask)
        TextView mTvMask;

        public PickViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_grid_pick);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(Hero hero) {
            mIvThumb.setImageResource(hero.mResID);
            if (mMode == MODE_PICK) {
                mIvThumb.setSelected(mSelected.contains(hero) && hero.isCheck());
                mIvStatus.setVisibility(mSelected.contains(hero) && hero.isCheck() ? View.VISIBLE : View.GONE);
                mTvMask.setVisibility(View.GONE);
            } else if (mMode == MODE_BAN) {
                mIvStatus.setVisibility(View.GONE);
                mTvMask.setVisibility(hero.isCheck() ? View.VISIBLE : View.GONE);
            } else {
                mIvStatus.setVisibility(View.GONE);
                mTvMask.setVisibility(hero.isBan() ? View.VISIBLE : View.GONE);
            }
            if (mListener != null) {
                itemView.setOnClickListener(v -> mListener.onItemClick(hero));
            }
        }

    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Hero hero);
    }

}
