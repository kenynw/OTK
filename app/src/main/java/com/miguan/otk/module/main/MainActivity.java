package com.miguan.otk.module.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Splash;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseDataActivity<MainPresenter, Splash> {

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

    @Override
    public void setData(Splash splash) {
        if (!TextUtils.isEmpty(splash.getImg())) {
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_ad_main, null);
            AlertDialog dialog = new AlertDialog.Builder(this).setView(view).show();

            SimpleDraweeView image = (SimpleDraweeView) view.findViewById(R.id.dv_ad_image);
            Button btn = (Button) view.findViewById(R.id.btn_ad_close);
            image.setImageURI(Uri.parse(splash.getImg()));
            btn.setOnClickListener(v -> dialog.dismiss());
        }
    }

    public ViewGroup getContainer() {
        return mContainer;
    }

}
