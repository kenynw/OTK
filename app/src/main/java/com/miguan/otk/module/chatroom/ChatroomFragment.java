package com.miguan.otk.module.chatroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

/**
 * Copyright (c) 2017/2/15. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(ChatRoomPresenter.class)
public class ChatRoomFragment extends ChainFragment<ChatRoomPresenter> {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.chat_room_message_fragment, container, false);
        return mRootView;
    }

    public View getRootView() {
        return mRootView;
    }

}
