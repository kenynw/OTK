package com.miguan.otk.module.chatroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.R;

/**
 * Copyright (c) 2017/2/27. LiaoPeiKun Inc. All rights reserved.
 */

public class ChatRoomActivityPresenter extends Presenter<ChatRoomActivity> {

    private static final String EXTRA_PARENT_ID = "room_parent_id";

    private int mID;

    public static void start(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, ChatRoomActivity.class);
        intent.putExtra(EXTRA_PARENT_ID, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(ChatRoomActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mID = getView().getIntent().getIntExtra(EXTRA_PARENT_ID, 0);
    }

    @Override
    protected void onCreateView(ChatRoomActivity view) {
        super.onCreateView(view);
        initChatRoomFragment();
    }

    private void initChatRoomFragment() {
        FragmentTransaction ft = getView().getSupportFragmentManager().beginTransaction();
        ChatRoomFragment chatRoomFragment = ChatRoomFragmentPresenter.newInstance(mID, "b");
        ft.replace(R.id.chat_room_fragment, chatRoomFragment);
        ft.commit();
    }

}
