package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Goods;
import com.miguan.otk.model.bean.Order;
import com.miguan.otk.module.store.OrderDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */

public class OrderViewHolder extends BaseViewHolder<Order> {

    @Bind(R.id.tv_order_num)
    TextView mTvNum;

    @Bind(R.id.tv_order_state)
    TextView mTvState;

    @Bind(R.id.dv_goods_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_spec)
    TextView mTvSpec;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    public OrderViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_order);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Order data) {
        mTvNum.setText(String.format(getContext().getString(R.string.label_order_num), data.getOrder_num()));
        mTvState.setText(data.getState());

        Goods goods = data.getGoods();
        mDvThumb.setImageURI(Uri.parse(goods.getGoods_image_url()));
        mTvName.setText(goods.getGoods_name());
        mTvSpec.setText(data.getSpec());
        mTvPrice.setText(goods.getGoods_price());
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), OrderDetailActivity.class);
            i.putExtra("order_id", data.getOrder_id());
            getContext().startActivity(i);
        });
    }
}
