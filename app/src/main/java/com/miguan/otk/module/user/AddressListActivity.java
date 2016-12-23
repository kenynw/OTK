package com.miguan.otk.module.user;

import android.os.Bundle;
import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.AddressViewHolder;

@RequiresPresenter(AddressListPresenter.class)
public class AddressListActivity extends BaseListActivity<AddressListPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_address);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar_list;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(parent);
    }
}
