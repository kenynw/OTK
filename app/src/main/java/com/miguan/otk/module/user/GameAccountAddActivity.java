package com.miguan.otk.module.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GameAccountAddPresenter.class)
public class GameAccountAddActivity extends ChainBaseActivity<GameAccountAddPresenter> {

    @Bind(R.id.grid_game_type)
    ExGridView mGridType;

    @Bind(R.id.et_game_account)
    EditText mEtAccount;

    @Bind(R.id.btn_game_save)
    Button mBtnSave;

    private TextView mCurChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_game_account_add);
        setToolbarTitle(R.string.title_activity_add_account);
        ButterKnife.bind(this);

        mBtnSave.setOnClickListener(v -> checkInput());

        for (int i=0; i<mGridType.getChildCount(); i++) {
            TextView tv = (TextView) mGridType.getChildAt(i);
            if (i == 0) {
                mCurChecked = tv;
                tv.setSelected(true);
            }
            tv.setTag(i);
            tv.setOnClickListener(v -> {
                mCurChecked.setSelected(false);
                v.setSelected(true);
                mCurChecked = tv;
            });
        }
    }

    private void checkInput() {
        if (TextUtils.isEmpty(mEtAccount.getText())) {
            LUtils.toast("游戏帐号不能为空");
            return;
        }
        getPresenter().save(mCurChecked.getText().toString().trim(), mEtAccount.getText().toString().trim());
    }

}
