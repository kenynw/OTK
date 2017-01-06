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
import com.miguan.otk.model.bean.League;
import com.miguan.otk.module.league.AddLeagueActivity;
import com.miguan.otk.module.league.LeagueDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LPK on 2016/11/22.
 */
@RequiresPresenter(MainLeaguePresenter.class)
public class MainLeagueFragment extends BaseDataFragment<MainLeaguePresenter, League> {

    @Bind(R.id.tv_league_detail)
    Button mBtnDetail;

    @Bind(R.id.tv_league_add)
    Button mBtnAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_league, null);
        ButterKnife.bind(this, view);

        mBtnDetail.setOnClickListener(v -> startActivity(new Intent(getActivity(), LeagueDetailActivity.class)));
        mBtnAdd.setOnClickListener(v -> startActivity(new Intent(getActivity(), AddLeagueActivity.class)));

        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

}
