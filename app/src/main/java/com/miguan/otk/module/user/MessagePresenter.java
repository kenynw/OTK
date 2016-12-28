package com.miguan.otk.module.user;

import android.content.Intent;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.Message;

import rx.Observable;

/**
 * Copyright (c) 2016/12/26. LiaoPeiKun Inc. All rights reserved.
 */

class MessagePresenter extends BaseDataActivityPresenter<MessageActivity, Message> {

    @Override
    protected void onCreateView(MessageActivity view) {
        super.onCreateView(view);
        Message message = new Message();
        message.setMessage_desc("欢迎参加OTK双12天选赛，报名即可获万元奖金");
        Observable.just(message).unsafeSubscribe(getDataSubscriber());
    }

    public void showMessageList(int type) {
        Intent intent = new Intent(getView(), MessageListActivity.class);
        intent.putExtra("type", type);
        getView().startActivity(intent);
    }

}
