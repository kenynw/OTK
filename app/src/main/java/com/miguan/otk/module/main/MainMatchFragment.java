package com.miguan.otk.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.miguan.otk.R;
import com.miguan.otk.adapter.BannerPagerAdapter;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.module.match.MissionListActivity;
import com.miguan.otk.module.user.MyMatchActivity;
import com.miguan.otk.widget.CirclePageIndicator;
import com.miguan.otk.widget.HeadViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LPK on 2016/11/22.
 */
@RequiresPresenter(MainMatchPresenter.class)
public class MainMatchFragment extends BaseDataFragment<MainMatchPresenter, Match> {

    @Bind(R.id.pager_match_banner)
    HeadViewPager mBannerPager;

    @Bind(R.id.indicator_match_banner)
    CirclePageIndicator mIndicator;

    @Bind(R.id.btn_daily_mission)
    Button mBtnDaily;

    @Bind(R.id.btn_my_match)
    Button mBtnMyMatch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_match, null);
        ButterKnife.bind(this, view);

        mBtnDaily.setOnClickListener(v -> startActivity(new Intent(getActivity(), MissionListActivity.class)));
        mBtnMyMatch.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyMatchActivity.class)));

        mBannerPager.setAdapter(new BannerPagerAdapter(getActivity()));
        mIndicator.setViewPager(mBannerPager);
        initTab();
        return view;
    }

    @Override
    public void setData(Match match) {
        if (match == null) {

        }
    }

    private void initTab() {
//        mTab.addOnTabSelectedListener(getPresenter());
//        String[] titles = getActivity().getResources().getStringArray(R.array.tab_match_list);
//        for (int i=0; i<titles.length; i++) {
//            TabLayout.Tab tab = mTab.newTab();
//            mTab.addTab(tab);
//            tab.setText(titles[i]);
//        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

}
