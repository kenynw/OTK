package com.miguan.otk.module.league;

import android.os.Bundle;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.League;

import butterknife.ButterKnife;

@RequiresPresenter(LeagueDetailPresenter.class)
public class LeaguDetailActivity extends BaseDataActivity<LeagueDetailPresenter, League> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leagu_activity_detail);
        setToolbarTitle(R.string.title_activity_league);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(League league) {

    }
}
