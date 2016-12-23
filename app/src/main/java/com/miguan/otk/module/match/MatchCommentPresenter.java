package com.miguan.otk.module.match;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchCommentPresenter extends BaseListFragmentPresenter {

    @Override
    public void onRefresh() {
        List<Comment> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Comment comment = new Comment();
            comment.setAvatar("");
            comment.setUsername("");
            comment.setContent("");
            comment.setDate("");
            comment.setLike_num("");
            list.add(comment);
        }
    }
}
