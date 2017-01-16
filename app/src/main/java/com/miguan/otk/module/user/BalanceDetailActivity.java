package com.miguan.otk.module.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.model.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BalanceDetailPresenter.class)
public class BalanceDetailActivity extends BaseDataActivity<BalanceDetailPresenter, User> {

    @Bind(R.id.tv_balance_sb)
    TextView mTvSb;

    @Bind(R.id.tv_balance_money)
    TextView mTvMoney;

    @Bind(R.id.tab_balance_detail)
    TabLayout mTabLayout;

    @Bind(R.id.pager_balance_detail)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_balance_detail);
        setToolbarTitle(R.string.title_activity_balance_detail);
        ButterKnife.bind(this);

        List<Fragment> list = new ArrayList<>();

        Bundle sb = new Bundle();
        BalanceListFragment scoreFragment = new BalanceListFragment();
        sb.putString("type", BalanceListPresenter.BALANCE_SCORE);
        scoreFragment.setArguments(sb);
        list.add(scoreFragment);

        Bundle mb = new Bundle();
        BalanceListFragment moneyFragment = new BalanceListFragment();
        mb.putString("type", BalanceListPresenter.BALANCE_MONEY);
        moneyFragment.setArguments(mb);
        list.add(moneyFragment);

        mPager.setAdapter(new TitlePagerAdapter(this, R.array.tab_balance_detail, list, getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }

    @Override
    public void setData(User user) {
        mTvSb.setText(String.format("我的撒币：\n%s", user.getCurrency()));
        mTvMoney.setText(String.format("我的元宝：\n%s", user.getMoney()));
    }
}
