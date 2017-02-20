package com.miguan.otk.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.WithdrawViewHolder;
import com.miguan.otk.model.bean.Withdraw;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(WithdrawListPresenter.class)
public class WithdrawListActivity extends BaseListActivity<WithdrawListPresenter> {

    @Bind(R.id.tv_withdraw_balance)
    TextView mTvBalance;

    @Bind(R.id.tv_withdraw_account)
    TextView mTvAccount;

    @Bind(R.id.btn_withdraw_withdraw)
    Button mBtnWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.btn_withdraw_record);
        ButterKnife.bind(this);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new WithdrawViewHolder(parent);
    }

    public void setData(Withdraw withdraw) {
        LUtils.log("set data");
        if (TextUtils.isEmpty(withdraw.getAlipay_account())) {
            mBtnWithdraw.setText(R.string.btn_bind_alipay);
            mBtnWithdraw.setOnClickListener(v -> startActivity(new Intent(this, BindAlipayActivity.class)));
        } else {
            mBtnWithdraw.setText(R.string.btn_withdraw);
            mTvBalance.setText(withdraw.getMoney() + "");
            mTvAccount.setText(withdraw.getAlipay_account());
            if (withdraw.getMoney() <= 15) {
                mBtnWithdraw.setEnabled(false);
            } else {
                mBtnWithdraw.setOnClickListener(v -> showCashDialog(withdraw.getMoney()));
            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_withdraw;
    }

    private void showCashDialog(int money) {
        View view = View.inflate(this, R.layout.dialog_withdraw, null);
        TextView tvClose = (TextView) view.findViewById(R.id.tv_dialog_close);
        Button btnOk = (Button) view.findViewById(R.id.btn_dialog_ok);
        EditText etAmount = (EditText) view.findViewById(R.id.et_dialog_withdraw_amount);
        etAmount.setHint("可提现" + money);
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    int num = Integer.valueOf(s.toString());
                    if (num > money) etAmount.setText(money + "");
                } else {
                    etAmount.setText("15");
                }
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(view);
        tvClose.setOnClickListener(v -> dialog.dismiss());
        btnOk.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etAmount.getText())) return;
            dialog.dismiss();
            getPresenter().withdraw(Integer.valueOf(etAmount.getText().toString()));
        });
        dialog.show();
    }

}
