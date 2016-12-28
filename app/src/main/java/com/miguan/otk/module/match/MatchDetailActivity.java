package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.model.bean.Match;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MatchDetailPresenter.class)
public class MatchDetailActivity extends BaseDataActivity<MatchDetailPresenter, Match> {

    private static final String[] TITLES = new String[] {"赛事信息", "赛事规则", "对阵表", "聊天室"};

    @Bind(R.id.tab_match_detail)
    TabLayout mTabLayout;

    @Bind(R.id.pager_match_detail)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_detail);
        ButterKnife.bind(this);

        List<Fragment> list = new ArrayList<>();
        list.add(new MatchInfoFragment());
        list.add(new MatchRulesFragment());
        list.add(new ScheduleFragment());
        list.add(new MatchInfoFragment());
        mPager.setAdapter(new TitlePagerAdapter(this, TITLES, list, getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }

}
