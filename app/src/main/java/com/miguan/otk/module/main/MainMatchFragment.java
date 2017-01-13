package com.miguan.otk.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.miguan.otk.R;
import com.miguan.otk.adapter.BannerPagerAdapter;
import com.miguan.otk.adapter.HomeMatchAdapter;
import com.miguan.otk.model.bean.Home;
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
public class MainMatchFragment extends BaseDataFragment<MainMatchPresenter, Home> {

    @Bind(R.id.swipe_main_match_refresh)
    SwipeRefreshLayout mRefreshLayout;

    @Bind(R.id.pager_match_banner)
    HeadViewPager mBannerPager;

    @Bind(R.id.indicator_match_banner)
    CirclePageIndicator mIndicator;

    @Bind(R.id.btn_daily_mission)
    Button mBtnDaily;

    @Bind(R.id.btn_my_match)
    Button mBtnMyMatch;

    @Bind(R.id.rv_main_home_commend)
    RecyclerView mRcvCommend;

    @Bind(R.id.rv_main_home_today)
    RecyclerView mRcvToday;

    @Bind(R.id.rv_main_home_recent)
    RecyclerView mRcvRecent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_match, container, false);
        ButterKnife.bind(this, view);

        mRefreshLayout.setOnRefreshListener(getPresenter());

        mRcvCommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcvCommend.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRcvToday.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcvToday.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRcvRecent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcvRecent.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mBtnDaily.setOnClickListener(v -> startActivity(new Intent(getActivity(), MissionListActivity.class)));
        mBtnMyMatch.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyMatchActivity.class)));

        return view;
    }

    @Override
    public void setData(Home home) {
        mBannerPager.setAdapter(new BannerPagerAdapter(getActivity(), home.getBanners()));
        mIndicator.setViewPager(mBannerPager);

        mRcvCommend.setAdapter(new HomeMatchAdapter(getActivity(), home.getRecommends()));
        mRcvToday.setAdapter(new HomeMatchAdapter(getActivity(), home.getTodays()));
        mRcvRecent.setAdapter(new HomeMatchAdapter(getActivity(), home.getLatelys()));
        if (mRefreshLayout.isRefreshing()) mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

}
