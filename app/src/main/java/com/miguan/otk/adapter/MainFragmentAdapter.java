package com.miguan.otk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miguan.otk.R;
import com.miguan.otk.module.main.MainMatchFragment;
import com.miguan.otk.module.main.MainMineFragment;
import com.miguan.otk.module.main.MainNewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LPK on 2016/11/22.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private static final String[] TITLES = new String[] {"比赛", "资讯", "个人"};

    private static final int[] sICONS = new int[] {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    private List<Fragment> mFragments;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(new MainMatchFragment());
        mFragments.add(new MainNewsFragment());
        mFragments.add(new MainMineFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    public int getPageIcon(int position) {
        return sICONS[position];
    }

}
