package com.miguan.otk.module.user;

import android.os.Bundle;
import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.MessageViewHolder;

@RequiresPresenter(MessageListPresenter.class)
public class MessageListActivity extends BaseListActivity<MessageListPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(getIntent().getIntExtra("type", 0) == 1 ? R.string.title_activity_message_match : R.string.title_activity_message_system);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar_list;
    }

}
