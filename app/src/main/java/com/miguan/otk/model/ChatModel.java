package com.miguan.otk.model;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.config.UserPreferences;
import com.miguan.otk.model.bean.Chatroom;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;

import rx.Observable;

/**
 * Copyright (c) 2017/2/22. LiaoPeiKun Inc. All rights reserved.
 */

public class ChatModel extends AbsModel {

    public static ChatModel getInstance() {
        return getInstance(ChatModel.class);
    }

    public Observable<Chatroom> getChatroomID(String type, int id) {
        return ServicesClient.getServices().chatRoomID(UserPreferences.getToken(), type, id)
                .compose(new DefaultTransform<>());
    }

}
