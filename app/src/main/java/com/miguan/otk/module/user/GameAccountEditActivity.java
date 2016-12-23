package com.miguan.otk.module.user;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.dsk.chain.bijection.BeamBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GameAccountEditPresenter.class)
public class GameAccountEditActivity extends BeamBaseActivity<GameAccountEditPresenter> {

    @Bind(R.id.et_game_edit_name)
    EditText mEtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_game_account_edit);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().save(mEtName.getText().toString().trim());
        return super.onOptionsItemSelected(item);
    }
}
