package com.miguan.otk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.miguan.otk.R;
import com.sgun.utils.LUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/22. LiaoPeiKun Inc. All rights reserved.
 */

public class PickAdapter extends RecyclerView.Adapter<PickAdapter.PickViewHolder> {

    private Context mContext;

    private String[] mNames;

    private List<String> mSelected;

    public PickAdapter(Context context) {
        this(context, context.getResources().getStringArray(R.array.items_pick_list));
    }

    public PickAdapter(Context context, String[] names) {
        mContext = context;
        mNames = names;
        mSelected = new ArrayList<>();
    }

    @Override
    public PickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PickViewHolder(View.inflate(mContext, R.layout.item_grid_pick, null));
    }

    @Override
    public void onBindViewHolder(PickViewHolder holder, int position) {
        holder.mCbName.setText(mNames[position]);
        holder.mCbName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mSelected.size() < 4) mSelected.add(mNames[position]);
                    else {
                        buttonView.setChecked(false);
                        LUtils.toast("最多可选择4张");
                    }
                } else {
                    mSelected.remove(mNames[position]);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    public Map<String, String> getSelected() {
        if (mSelected != null && mSelected.size() > 0) {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < mSelected.size(); i++) {
                map.put("car" + (i + 1), mSelected.get(i));
            }
            return map;
        }
        return null;
    }

    class PickViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_pick_thumb)
        ImageView mIvThumb;

        @Bind(R.id.cb_pick_name)
        CheckBox mCbName;

        public PickViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

}
