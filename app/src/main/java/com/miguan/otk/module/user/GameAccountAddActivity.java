package com.miguan.otk.module.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GameAccountAddPresenter.class)
public class GameAccountAddActivity extends ChainBaseActivity<GameAccountAddPresenter> {

    @Bind(R.id.rg_game_name)
    RadioGroup mRGroupName;

    @Bind(R.id.et_game_account)
    EditText mEtAccount;

    @Bind(R.id.btn_game_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_game_account_add);
        ButterKnife.bind(this);

        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void checkInput() {
        if (TextUtils.isEmpty(mEtAccount.getText())) {
            LUtils.toast("游戏帐号不能为空");
            return;
        }
        RadioButton rbtn = (RadioButton) findViewById(mRGroupName.getCheckedRadioButtonId());
        getPresenter().save(rbtn.getText().toString().trim(), mEtAccount.getText().toString().trim());
    }

}
