package com.miguan.otk.module.battle;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ShotBrowsePresenter.class)
public class ShotBrowseActivity extends ChainBaseActivity<ShotBrowsePresenter> {

    @Bind(R.id.dv_image_browse)
    SimpleDraweeView mDvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity_image_browse);
        ButterKnife.bind(this);

        Uri uri = getIntent().getParcelableExtra("uri");
        mDvImage.setImageURI(uri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int battleID = getIntent().getIntExtra("battle_id", 0);
        if (battleID > 0) getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().upload();
        return super.onOptionsItemSelected(item);
    }
}
