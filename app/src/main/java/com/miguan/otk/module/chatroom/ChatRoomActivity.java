package com.miguan.otk.module.chatroom;

import android.os.Bundle;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

@RequiresPresenter(ChatRoomActivityPresenter.class)
public class ChatRoomActivity extends ChainBaseActivity<ChatRoomActivityPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room_activity);
        setToolbarTitle(R.string.title_activity_chat_room);
    }

}
