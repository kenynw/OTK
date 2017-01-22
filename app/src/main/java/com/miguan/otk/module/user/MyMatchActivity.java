package com.miguan.otk.module.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.module.match.MatchRecordFragment;
import com.miguan.otk.module.battle.BattleRecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MyMatchPresenter.class)
public class MyMatchActivity extends ChainBaseActivity<MyMatchPresenter> {

    @Bind(R.id.tab_my_match)
    TabLayout mTabLayout;

    @Bind(R.id.pager_my_match)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_my_match);
        setToolbarTitle(R.string.title_activity_my_match);
        ButterKnife.bind(this);

        mPager.setAdapter(new TitlePagerAdapter(this, R.array.tab_my_match, getFragments(), getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        MatchRecordFragment mlf = new MatchRecordFragment();
        fragments.add(mlf);
        BattleRecordFragment mrf = new BattleRecordFragment();
        fragments.add(mrf);
        return fragments;
    }

}
