package com.miguan.otk.module.battle;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.model.bean.Battle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ShotDetailPresenter.class)
public class ShotDetailActivity extends BaseDataActivity<ShotDetailPresenter, Battle> {

    @Bind(R.id.tab_submit_shot)
    TabLayout mTab;

    @Bind(R.id.pager_submit_shot)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_submit_shot);
        setToolbarTitle(R.string.title_activity_submit_shot);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Battle battle) {
        List<Fragment> list = new ArrayList<>();

        ShotListFragment fragmentA = new ShotListFragment();
        Bundle bundleA = new Bundle();
        bundleA.putParcelable("battle", battle);
        bundleA.putInt("type", 1);
        fragmentA.setArguments(bundleA);
        list.add(fragmentA);

        ShotListFragment fragmentB = new ShotListFragment();
        Bundle bundleB = new Bundle();
        bundleB.putParcelable("battle", battle);
        bundleB.putInt("type", 2);
        fragmentB.setArguments(bundleB);
        list.add(fragmentB);

        mPager.setAdapter(new TitlePagerAdapter(this, new String[] {battle.getA_username(), battle.getB_username()}, list, getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }
}
