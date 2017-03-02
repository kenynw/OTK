package com.miguan.otk.module.chatroom;

import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.config.UserPreferences;
import com.miguan.otk.model.ChatModel;
import com.miguan.otk.model.bean.Chatroom;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.session.module.Container;
import com.netease.nim.uikit.session.module.ModuleProxy;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.ChatRoomServiceObserver;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomKickOutEvent;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomStatusChangeData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.List;

/**
 * Copyright (c) 2017/2/21. LiaoPeiKun Inc. All rights reserved.
 */

public class ChatRoomFragmentPresenter extends Presenter<ChatRoomFragment> implements ModuleProxy {

    private static final String EXTRA_PARENT_ID = "parent_id";

    private static final String EXTRA_ROOM_TYPE = "room_type";

    private int mParentID = 0;

    private String mRoomType;

    private String mRoomID;

    private boolean mHasEnterSuccess; // 是否进入聊天室

    private ChatRoomMsgListPanel mMessageListPanel;

    private ChatRoomInputPanel mInputPanel;

    public static ChatRoomFragment newInstance(int id, String roomType) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_PARENT_ID, id);
        args.putString(EXTRA_ROOM_TYPE, roomType);
        ChatRoomFragment fragment = new ChatRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onCreate(ChatRoomFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) {
            mParentID = getView().getArguments().getInt(EXTRA_PARENT_ID);
            mRoomType = getView().getArguments().getString(EXTRA_ROOM_TYPE);
        }
    }

    @Override
    protected void onCreateView(ChatRoomFragment view) {
        super.onCreateView(view);
        getChatRoomID();
    }

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        logout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logout();
    }

    private void doLogin() {
        String account = UserPreferences.getUserID();
        String token = UserPreferences.getNIMToken();

        NimUIKit.doLogin(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo info) {
                getChatRoomID();
                LUtils.log("登录成功 account: " + info.getAccount() + ", token: " + info.getToken());
            }

            @Override
            public void onFailed(int code) {
                if (code == 302 || code == 404) {
                    LUtils.toast("账号或密码错误");
                } else {
                    LUtils.toast("登录失败: " + code);
                }
                logout();
            }

            @Override
            public void onException(Throwable throwable) {
                LUtils.toast("无效输入");
            }
        });
    }

    public void getChatRoomID() {
        ChatModel.getInstance().getChatroomID(mRoomType, mParentID)
                .unsafeSubscribe(new ServicesResponse<Chatroom>() {
                    @Override
                    public void onNext(Chatroom chatroom) {
                        mRoomID = chatroom.getRoom_id();

                        enterRoom();
                    }
                });
    }

    private void enterRoom() {
        mHasEnterSuccess = false;

        EnterChatRoomData data = new EnterChatRoomData(mRoomID);
        ChatRoomService roomService = NIMClient.getService(ChatRoomService.class);
        roomService.enterChatRoomEx(data, 1)
                .setCallback(new RequestCallback<EnterChatRoomResultData>() {
                    @Override
                    public void onSuccess(EnterChatRoomResultData param) {
                        registerObservers(true);

                        initModules();
                    }

                    @Override
                    public void onFailed(int code) {
                        LUtils.log("onFailed" + code);
                    }

                    @Override
                    public void onException(Throwable exception) {
                        LUtils.log("onException");
                    }
                });
    }

    private void initModules() {
        Container container = new Container(getView().getActivity(), mRoomID, SessionTypeEnum.ChatRoom, this);
        if (mMessageListPanel == null) {
            mMessageListPanel = new ChatRoomMsgListPanel(container, getView().getRootView());
        }

        if (mInputPanel == null) {
            mInputPanel = new ChatRoomInputPanel(container, getView().getRootView(), null, false);
        } else {
            mInputPanel.reload(container, null);
        }
    }

    private void registerObservers(boolean register) {
        // 监听在线状态
        NIMClient.getService(ChatRoomServiceObserver.class).observeOnlineStatus((Observer<ChatRoomStatusChangeData>) chatRoomStatusChangeData -> {
            if (!chatRoomStatusChangeData.roomId.equals(mRoomID)) {
                return;
            }
            if (chatRoomStatusChangeData.status == StatusCode.CONNECTING) {
                DialogMaker.updateLoadingMessage("连接中...");
            } else if (chatRoomStatusChangeData.status == StatusCode.LOGINING) {
                DialogMaker.updateLoadingMessage("登录中...");
            } else if (chatRoomStatusChangeData.status == StatusCode.LOGINED) {
            } else if (chatRoomStatusChangeData.status == StatusCode.UNLOGIN) {
                // 登录成功后，断网重连交给云信SDK，如果重连失败，可以查询具体失败的原因
                if (mHasEnterSuccess) {
                    int code = NIMClient.getService(ChatRoomService.class).getEnterErrorCode(mRoomID);
                    LUtils.toast("getEnterErrorCode=" + code);
                    LUtils.log("chat room enter error code:" + code);
                }
            } else if (chatRoomStatusChangeData.status == StatusCode.NET_BROKEN) {
                LUtils.toast("网络未连接");
            }

            LUtils.log("chat room online status changed to " + chatRoomStatusChangeData.status.name());
        }, register);

        // 监听被踢出聊天室
        NIMClient.getService(ChatRoomServiceObserver.class).observeKickOutEvent((Observer<ChatRoomKickOutEvent>) chatRoomKickOutEvent -> LUtils.toast("被踢出聊天室，原因:" + chatRoomKickOutEvent.getReason()), register);

        // 接收消息
        NIMClient.getService(ChatRoomServiceObserver.class).observeReceiveMessage((Observer<List<ChatRoomMessage>>) messages -> {
            if (messages == null || messages.isEmpty()) {
                return;
            }

            mMessageListPanel.onIncomingMessage(messages);

        }, register);
    }

    private void logout() {
        NIMClient.getService(ChatRoomService.class).exitChatRoom(mRoomID);
    }

    @Override
    public boolean sendMessage(IMMessage msg) {
        ChatRoomMessage message = (ChatRoomMessage) msg;

//        Map<String, Object> ext = new HashMap<>();
//        ChatRoomMember chatRoomMember = ChatRoomMemberCache.getInstance().getChatRoomMember(roomId, DemoCache.getAccount());
//        if (chatRoomMember != null && chatRoomMember.getMemberType() != null) {
//            ext.put("type", chatRoomMember.getMemberType().getValue());
//            message.setRemoteExtension(ext);
//        }

        NIMClient.getService(ChatRoomService.class).sendMessage(message, false)
                .setCallback(new RequestCallback<Void>() {
                    @Override
                    public void onSuccess(Void param) {
                    }

                    @Override
                    public void onFailed(int code) {
                        if (code == ResponseCode.RES_CHATROOM_MUTED) {
                            LUtils.toast("用户被禁言");
                        } else if (code == ResponseCode.RES_CHATROOM_ROOM_MUTED) {
                            LUtils.toast("全体禁言");
                        } else {
                            LUtils.toast("消息发送失败：code:" + code);
                        }
                    }

                    @Override
                    public void onException(Throwable exception) {
                        LUtils.toast("消息发送失败！");
                    }
                });
        mMessageListPanel.onMsgSend(message);
        return true;
    }

    @Override
    public void onInputPanelExpand() {
        mMessageListPanel.scrollToBottom();
    }

    @Override
    public void shouldCollapseInputPanel() {
        mInputPanel.collapse(false);
    }

    @Override
    public boolean isLongClickEnabled() {
        return !mInputPanel.isRecording();
    }

}
