package com.miguan.otk.module.league;

import android.os.Bundle;

import com.dsk.chain.bijection.BeamBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.ButterKnife;

@RequiresPresenter(AddLeaguePresenter.class)
public class AddLeagueActivity extends BeamBaseActivity<AddLeaguePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_activity_add);
        setToolbarTitle(R.string.title_activity_league_add);
        ButterKnife.bind(this);


    }
}
