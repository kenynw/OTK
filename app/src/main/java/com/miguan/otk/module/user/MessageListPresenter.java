package com.miguan.otk.module.user;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.bean.Message;

/**
 * Copyright (c) 2016/12/26. LiaoPeiKun Inc. All rights reserved.
 */

class MessageListPresenter extends BaseListActivityPresenter<MessageListActivity, Message> {

    @Override
    protected void onCreateView(MessageListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {

    }
}
