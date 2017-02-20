package com.miguan.otk.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Space;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.GameViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GameAccountPresenter.class)
public class GameAccountActivity extends BaseListActivity<GameAccountPresenter> {

    @Bind(R.id.btn_game_add)
    Button mBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.title_activity_game_account);
        ButterKnife.bind(this);
        mBtnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameAccountAddActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_game_account;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new GameViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        Space space = new Space(this);
        return super.getListConfig().setFooterMoreView(space).setFooterNoMoreView(space);
    }
}
