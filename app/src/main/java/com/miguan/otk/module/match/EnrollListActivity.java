package com.miguan.otk.module.match;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.EnrollViewHolder;

@RequiresPresenter(EnrollListPresenter.class)
public class EnrollListActivity extends BaseListActivity<EnrollListPresenter> {

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar_list;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new EnrollViewHolder(parent);
    }

}
