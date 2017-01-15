package com.miguan.otk.module.match;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.CompetitorViewHolder;

@RequiresPresenter(CompetitorListPresenter.class)
public class CompetitorListActivity extends BaseListActivity<CompetitorListPresenter> {

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar_list;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new CompetitorViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setLoadMoreAble(false).setNoMoreAble(false).setFooterErrorAble(false);
    }

}
