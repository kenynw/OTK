package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.module.against.ScreenshotFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubmitShotActivity extends BaseDataActivity<SubmitShotPresenter, Match> {

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

        List<Fragment> list = new ArrayList<>();
        list.add(new ScreenshotFragment());
        list.add(new ScreenshotFragment());

        mPager.setAdapter(new TitlePagerAdapter(this, new String[] {"机智的", "黄图哥"}, list, getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }
}
