package com.miguan.otk.module.match;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.model.bean.Match;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MatchDetailPresenter.class)
public class MatchDetailActivity extends BaseDataActivity<MatchDetailPresenter, Match> {

    @Bind(R.id.tv_match_detail_title)
    TextView mTvTitle;

    @Bind(R.id.tv_match_detail_cost)
    TextView mTvCost;

    @Bind(R.id.tv_match_detail_id)
    TextView mTvID;

    @Bind(R.id.tv_match_detail_status)
    TextView mTvStatus;

    @Bind(R.id.btn_match_detail_status)
    Button mBtnStatus;

    @Bind(R.id.id_stickynavlayout_tab)
    TabLayout mTabLayout;

    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager mPager;

    private Match mMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_detail);
        setToolbarTitle(R.string.title_activity_match_detail);
        ButterKnife.bind(this);

        mPager.setAdapter(new TitlePagerAdapter(this, R.array.tab_match_detail, getPresenter().getFragments(), getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() > 1) mBtnStatus.setVisibility(View.GONE);
                else mBtnStatus.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setData(Match match) {
        EventBus.getDefault().post(match);
        mMatch = match;
        mTvID.setText(String.format(getString(R.string.label_match_id), match.getCompetition_id()));
        mTvCost.setText(String.format(getString(R.string.label_match_cost), match.getCost()));
        mTvTitle.setText(match.getTitle());
        mTvStatus.setText(String.format(getString(R.string.label_match_state), match.getGame_desc(), getFormatDate(Long.valueOf(match.getGame_time()) * 1000)));
        mBtnStatus.setText(match.getGame_status());
        new CountDownTimer(Long.valueOf(match.getGame_time()) * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTvStatus.setText(String.format(getString(R.string.label_match_state), match.getGame_desc(), getFormatDate(millisUntilFinished)));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().share(mMatch);
        return super.onOptionsItemSelected(item);
    }

    private String getFormatDate(long time) {
        String days = (time / (1000 * 3600 * 24)) + "";
        String hours = ((time % (1000 * 3600 * 24)) / (1000 * 3600)) + "";
        String minutes = ((time % (1000 * 3600)) / (1000 * 60)) + "";
        String seconds = (time % (1000 * 60) / 1000) + "";
        return String.format("%1$s:%2$s:%3$s:%4$s", days, hours, minutes, seconds);
    }

}
