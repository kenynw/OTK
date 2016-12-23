package com.miguan.otk.module.match;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.adapter.viewholder.CommentViewHolder;
import com.miguan.otk.model.bean.Comment;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MatchCommentPresenter.class)
public class MatchCommentFragment extends BaseListFragment<MatchCommentPresenter, Comment> {

    @Override
    public BaseViewHolder<Comment> createViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(parent);
    }
}
