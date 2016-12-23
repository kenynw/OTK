package com.miguan.otk.module.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dsk.chain.bijection.BeamBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.module.store.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MyOrderPresenter.class)
public class MyOrderActivity extends BeamBaseActivity<MyOrderPresenter> {

    @Bind(R.id.tab_my_order)
    TabLayout mTabLayout;

    @Bind(R.id.pager_my_order)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_my_order);
        setToolbarTitle(R.string.title_activity_my_order);
        ButterKnife.bind(this);

        mPager.setAdapter(new TitlePagerAdapter(this, R.array.tab_my_order, getList(), getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }

    private List<Fragment> getList() {
        List<Fragment> list = new ArrayList<>();
        for (int i=0; i<2; i++) {
            OrderListFragment fragment = new OrderListFragment();
            list.add(fragment);
        }
        return list;
    }

}
