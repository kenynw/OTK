package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Goods;
import com.miguan.otk.module.store.GoodsDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class GoodsViewHolder extends BaseViewHolder<Goods> {

    @Bind(R.id.dv_goods_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    public GoodsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_store_goods);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Goods data) {
        mDvThumb.setImageURI(Uri.parse(data.getGoods_image_url()));
        mTvName.setText(data.getGoods_name());
        mTvPrice.setText(data.getGoods_price());
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
            intent.putExtra("goods_id", data.getGoods_id());
            intent.putExtra("goods", data);
            getContext().startActivity(intent);
        });
    }
}
