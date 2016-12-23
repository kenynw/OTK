package com.miguan.otk.module.store;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.GoodsViewHolder;

@RequiresPresenter(StoreHomePresenter.class)
public class StoreHomeActivity extends BaseListActivity<StoreHomePresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(getPresenter().getAdapter().obtainGridSpanSizeLookUp(2));
        getListView().setLayoutManager(gridLayoutManager);
        setToolbarTitle(R.string.title_activity_store);
    }

    @Override
    protected int getLayout() {
        return R.layout.store_activity_home;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new GoodsViewHolder(parent);
    }

}
