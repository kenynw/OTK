package com.miguan.otk.module.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Game;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GameAccountEditPresenter.class)
public class GameAccountEditActivity extends ChainBaseActivity<GameAccountEditPresenter> {

    @Bind(R.id.et_game_edit_name)
    EditText mEtName;

    @Bind(R.id.btn_game_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_game_account_edit);
        ButterKnife.bind(this);

        setData(getIntent().getParcelableExtra("game"));

        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void setData(Game game) {
        setToolbarTitle(game.getGame_name());
        mEtName.setText(game.getGame_account());
    }

    private void checkInput() {
        if (TextUtils.isEmpty(mEtName.getText())) {
            LUtils.toast("游戏名字不能为空");
            return;
        }

        getPresenter().save(mEtName.getText().toString().trim());
    }

}
