package com.miguan.otk.module.chatroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/2/15. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(ChatRoomFragmentPresenter.class)
public class ChatRoomFragment extends ChainFragment<ChatRoomFragmentPresenter> {

    private View mRootView;

    private View mInputView;

    @Bind(R.id.textMessageLayout)
    RelativeLayout mInputLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.chat_room_message_fragment, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    public View getRootView() {
        if (mInputView == null) {
            mInputLayout.setVisibility(View.VISIBLE);
            return mRootView;
        } else {
            LUtils.toast("get input layout");
            mInputLayout.setVisibility(View.GONE);
            return mInputView;
        }
    }

    public void setInputView(View view) {
        this.mInputView = view;
    }

}
