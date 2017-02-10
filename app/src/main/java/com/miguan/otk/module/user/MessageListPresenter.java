package com.miguan.otk.module.user;

import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Message;

/**
 * Copyright (c) 2016/12/26. LiaoPeiKun Inc. All rights reserved.
 */

public class MessageListPresenter extends BaseListActivityPresenter<MessageListActivity, Message> {

    private int mType;

    @Override
    protected void onCreate(MessageListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mType = getView().getIntent().getIntExtra("type", 1);
    }

    @Override
    protected void onCreateView(MessageListActivity view) {
        super.onCreateView(view);
        getView().setToolbarTitle(mType == 1 ? R.string.title_activity_message_system : R.string.title_activity_message_match);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getMessageList(mType, 1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        UserModel.getInstance().getMessageList(mType, getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
