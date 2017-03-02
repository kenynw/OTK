package com.miguan.otk.module.chatroom;

import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.widget.TextView;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.netease.nim.uikit.common.util.sys.ScreenUtil;
import com.netease.nim.uikit.session.emoji.MoonUtil;

/**
 * Created by hzxuwen on 2016/1/18.
 */
public class ChatRoomMsgViewHolderText extends ChatRoomMsgViewHolderBaseText {
    public ChatRoomMsgViewHolderText(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected boolean isShowHeadImage() {
        return false;
    }

    @Override
    public void setNameTextView() {
        nameContainer.setPadding(ScreenUtil.dip2px(6), 0, 0, 0);
        if (isReceivedMessage()) setGravity(nameTextView, Gravity.START);
        else setGravity(nameTextView, Gravity.END);
        ChatRoomViewHolderHelper.setNameTextView(message, nameTextView, nameIconView, context);
    }

    @Override
    protected void bindContentView() {
        TextView bodyTextView = findViewById(com.netease.nim.uikit.R.id.nim_message_item_text_body);
        bodyTextView.setTextColor(Color.BLACK);
        layoutDirection();
        MoonUtil.identifyFaceExpression(NimUIKit.getContext(), bodyTextView, getDisplayText(), ImageSpan.ALIGN_BOTTOM);
        bodyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        bodyTextView.setOnLongClickListener(longClickListener);
    }

    private void layoutDirection() {
        TextView bodyTextView = findViewById(com.netease.nim.uikit.R.id.nim_message_item_text_body);
        bodyTextView.setPadding(ScreenUtil.dip2px(6), 0, 0, 0);
    }
}
