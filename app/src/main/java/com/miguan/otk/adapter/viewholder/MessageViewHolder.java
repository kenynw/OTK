package com.miguan.otk.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Message;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/26. LiaoPeiKun Inc. All rights reserved.
 */

public class MessageViewHolder extends BaseViewHolder<Message> {

    @Bind(R.id.tv_message_date)
    TextView mTvDate;

    @Bind(R.id.tv_message_content)
    TextView mTvContent;

    public MessageViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_message);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Message data) {
        mTvDate.setText(data.getMessage_date());
        mTvContent.setText(String.format("%s\n%s", data.getMessage_title(), data.getMessage_desc()));
    }
}
