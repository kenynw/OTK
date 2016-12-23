package com.miguan.otk.module.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BalanceDetailPresenter.class)
public class BalanceDetailActivity extends BaseDataActivity {

    @Bind(R.id.tab_balance_detail)
    TabLayout mTabLayout;

    @Bind(R.id.pager_balance_detail)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_balance_detail);
        ButterKnife.bind(this);

        List<Fragment> list = new ArrayList<>();
        list.add(new BalanceListFragment());
        list.add(new BalanceListFragment());

        mPager.setAdapter(new TitlePagerAdapter(this, R.array.tab_balance_detail, list, getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }
}
