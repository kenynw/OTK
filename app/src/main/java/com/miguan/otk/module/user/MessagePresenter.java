package com.miguan.otk.module.user;

import android.content.Intent;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Message;

import java.util.List;

/**
 * Copyright (c) 2016/12/26. LiaoPeiKun Inc. All rights reserved.
 */

public class MessagePresenter extends BaseDataActivityPresenter<MessageActivity, List<Message>> {

    @Override
    protected void onCreateView(MessageActivity view) {
        super.onCreateView(view);
        UserModel.getInstance().getMessageDesc().unsafeSubscribe(getDataSubscriber());
    }

    public void showMessageList(int type) {
        Intent intent = new Intent(getView(), MessageListActivity.class);
        intent.putExtra("type", type);
        getView().startActivity(intent);
    }

}
