package com.miguan.otk.module.user;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.bean.Message;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

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
        List<Message> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Message message = new Message();
            message.setMessage_title("系统消息");
            message.setMessage_desc("【otk游戏竞技】您的比赛  炉石传说—OTK电竞常规赛#361   还有10分钟就要开始了，签到将于赛前5分钟结束，请您及时签到");
            message.setMessage_date("2016-11-15 12:52");
            list.add(message);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
