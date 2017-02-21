package com.miguan.otk.module.main;

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
import android.widget.TextView;

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
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LPK on 2016/11/22.
 */
@RequiresPresenter(MainMatchPresenter.class)
public class MainMatchFragment extends BaseDataFragment<MainMatchPresenter, Home> {

    @Bind(R.id.tv_main_match_commend)
    TextView mTvCommend;

    @Bind(R.id.tv_main_match_todays)
    TextView mTvTodays;

    @Bind(R.id.tv_main_match_recent)
    TextView mTvRecent;

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

        ViewGroup.LayoutParams lp = mBannerPager.getLayoutParams();
        lp.height = LUtils.getScreenWidth() *  8 / 15;
        mBannerPager.setLayoutParams(lp);

        mRefreshLayout.setOnRefreshListener(getPresenter());

        DividerItemDecoration spaceDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRcvCommend.addItemDecoration(spaceDecoration);
        mRcvToday.addItemDecoration(spaceDecoration);
        mRcvRecent.addItemDecoration(spaceDecoration);

        mRcvCommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcvToday.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcvRecent.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBtnDaily.setOnClickListener(v -> getPresenter().toActivity(MissionListActivity.class));
        mBtnMyMatch.setOnClickListener(v -> getPresenter().toActivity(MyMatchActivity.class));

        return view;
    }

    @Override
    public void setData(Home home) {
        mBannerPager.setAdapter(new BannerPagerAdapter(getActivity(), home.getBanners()));
        mIndicator.setViewPager(mBannerPager);

        if (home.getRecommends().size() > 0) {
            mRcvCommend.setVisibility(View.VISIBLE);
            mTvCommend.setVisibility(View.VISIBLE);
            mRcvCommend.setAdapter(new HomeMatchAdapter(getActivity(), home.getRecommends()));
        } else {
            mRcvCommend.setVisibility(View.GONE);
            mTvCommend.setVisibility(View.GONE);
        }
        if (home.getTodays().size() > 0) {
            mRcvToday.setVisibility(View.VISIBLE);
            mTvTodays.setVisibility(View.VISIBLE);
            mRcvToday.setAdapter(new HomeMatchAdapter(getActivity(), home.getTodays()));
        } else {
            mRcvToday.setVisibility(View.GONE);
            mTvTodays.setVisibility(View.GONE);
        }
        if (home.getLatelys().size() > 0) {
            mRcvRecent.setVisibility(View.VISIBLE);
            mTvRecent.setVisibility(View.VISIBLE);
            mRcvRecent.setAdapter(new HomeMatchAdapter(getActivity(), home.getLatelys()));
        } else {
            mRcvRecent.setVisibility(View.GONE);
            mTvRecent.setVisibility(View.GONE);
        }
        if (mRefreshLayout.isRefreshing()) mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    public void stopRefresh() {
        if (mRefreshLayout.isRefreshing()) mRefreshLayout.setRefreshing(false);
    }

}
