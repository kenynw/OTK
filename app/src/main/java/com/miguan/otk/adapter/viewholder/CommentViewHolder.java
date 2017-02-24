package com.miguan.otk.adapter.viewholder;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Comment;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */

public class CommentViewHolder extends BaseViewHolder<Comment> {

    @Bind(R.id.dv_comment_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_comment_name)
    TextView mTvName;

    @Bind(R.id.tv_comment_content)
    TextView mTvContent;

    @Bind(R.id.tv_comment_date)
    TextView mTvDate;

    @Bind(R.id.tv_comment_like)
    TextView mTvLike;

    public CommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_comment);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Comment comment) {
        mDvAvatar.setImageURI(Uri.parse(comment.getAvatar()));
        mTvName.setText(comment.getUsername());
        mTvContent.setText(comment.getContent());
        mTvDate.setText(comment.getDate());
        mTvLike.setText(comment.getLike_num());
        mTvLike.setOnClickListener(v -> LUtils.toast("点赞"));
    }
}
