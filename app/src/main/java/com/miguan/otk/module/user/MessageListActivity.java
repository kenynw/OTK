package com.miguan.otk.module.user;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.MessageViewHolder;

@RequiresPresenter(MessageListPresenter.class)
public class MessageListActivity extends BaseListActivity<MessageListPresenter> {

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar_list;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setHasItemDecoration(false);
    }
}
