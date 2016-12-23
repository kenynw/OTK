package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Game;
import com.miguan.otk.module.user.GameAccountEditActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class GameAccountViewHolder extends BaseViewHolder<Game> {

    @Bind(R.id.dv_game_icon)
    SimpleDraweeView mDvIcon;

    @Bind(R.id.tv_game_name)
    TextView mTvName;

    @Bind(R.id.tv_game_account)
    TextView mTvAccount;

    public GameAccountViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_game_account);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Game data) {
        mDvIcon.setImageURI(Uri.parse(data.getGame_icon()));
        mTvName.setText(data.getGame_name());
        mTvAccount.setText(data.getGame_account());
        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), GameAccountEditActivity.class);
            // TODO
//            i.putExtra("account_id", data.get);
            getContext().startActivity(i);
        });
    }
}
