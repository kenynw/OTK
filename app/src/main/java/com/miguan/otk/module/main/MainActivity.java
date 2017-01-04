package com.miguan.otk.module.main;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends ChainBaseActivity<MainPresenter> {

    @Bind(R.id.main_container)
    FrameLayout mContainer;

    @Bind(R.id.main_tab)
    TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        ButterKnife.bind(this);

        initTab();
    }

    private void initTab() {
        mTab.addOnTabSelectedListener(getPresenter());
        for (int i = 0; i < getPresenter().getAdapter().getCount(); i++) {
            TabLayout.Tab tab = mTab.newTab();
            mTab.addTab(tab, i == 0);
            tab.setText(getPresenter().getAdapter().getPageTitle(i));
            tab.setIcon(getPresenter().getAdapter().getPageIcon(i));
        }
    }

    public ViewGroup getContainer() {
        return mContainer;
    }

}
