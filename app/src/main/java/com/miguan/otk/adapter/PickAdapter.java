package com.miguan.otk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.miguan.otk.R;
import com.miguan.otk.model.bean.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/22. LiaoPeiKun Inc. All rights reserved.
 */

public class PickAdapter extends BaseAdapter {

    private final int[] HERO_RES = new int[] {
            R.mipmap.ic_warrior, R.mipmap.ic_mage, R.mipmap.ic_preist,
            R.mipmap.ic_rouge, R.mipmap.ic_paladin, R.mipmap.ic_warlock,
            R.mipmap.ic_druid, R.mipmap.ic_hunter, R.mipmap.ic_shaman
    };

    private Context mContext;

    private List<Hero> mHeroList;

    private List<Hero> mSelected;

    public PickAdapter(Context context) {
        String[] names = context.getResources().getStringArray(R.array.items_pick_list);
        for (int i=0; i<HERO_RES.length; i++) {
            Hero hero = new Hero();
            hero.setName(names[i]);
            hero.setIndex(i);
            mHeroList.add(hero);
        }
        mSelected = new ArrayList<>();
    }

    public PickAdapter(Context context, List<Hero> list) {
        mContext = context;
        mHeroList = list;
        mSelected = new ArrayList<>();
    }

    public PickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PickViewHolder(View.inflate(mContext, R.layout.item_grid_pick, null));
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
    public Hero getItem(int position) {
        return mHeroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_grid_pick, null);
        PickViewHolder holder = new PickViewHolder(view);
        holder.setData(mHeroList.get(position));

        return view;
    }

    class PickViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_pick_thumb)
        ImageView mIvThumb;

        @Bind(R.id.cb_pick_status)
        CheckBox mCbName;

        public PickViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Hero hero) {
            mIvThumb.setImageResource(hero.getRes());
            itemView.setOnClickListener(v -> {
                if (mSelected.contains(hero)) {
                    mSelected.remove(hero);
                    mCbName.setChecked(false);
                } else {
                    mSelected.add(hero);
                    mCbName.setChecked(true);
                }
            });
//            mCbName.setText(mNames[position]);
//            mCbName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        if (mSelected.size() < 4) mSelected.add(mNames[position]);
//                        else {
//                            buttonView.setChecked(false);
//                            LUtils.toast("最多可选择4张");
//                        }
//                    } else {
//                        mSelected.remove(mNames[position]);
//                    }
//                }
//            });
        }

    }

}
