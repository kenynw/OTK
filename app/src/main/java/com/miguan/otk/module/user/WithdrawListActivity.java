package com.miguan.otk.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
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

    @Bind(R.id.btn_withdraw_bind)
    Button mBtnBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(R.string.btn_withdraw_record);
        ButterKnife.bind(this);

        mBtnWithdraw.setOnClickListener(v -> showCashDialog());
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new WithdrawViewHolder(parent);
    }

    public void setData(Withdraw withdraw) {
        if (TextUtils.isEmpty(withdraw.getAlipay_account())) {
            mBtnBind.setVisibility(View.VISIBLE);
            mBtnWithdraw.setVisibility(View.GONE);
            mBtnBind.setOnClickListener(v -> startActivity(new Intent(this, BindAlipayActivity.class)));
        } else {
            mBtnBind.setVisibility(View.GONE);
            mBtnWithdraw.setVisibility(View.VISIBLE);
            mTvBalance.setText(getString(R.string.label_cash_balance) + withdraw.getMoney());
            mTvAccount.setText(withdraw.getAlipay_account());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_withdraw;
    }

    private void showCashDialog() {
        View view = View.inflate(this, R.layout.dialog_withdraw, null);
        TextView tvClose = (TextView) view.findViewById(R.id.tv_dialog_cash_in_close);
        Button btnOk = (Button) view.findViewById(R.id.btn_dialog_cash_in_ok);
        EditText etAmount = (EditText) view.findViewById(R.id.et_dialog_cash_in_amount);

        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(view);
        tvClose.setOnClickListener(v -> dialog.dismiss());
        btnOk.setOnClickListener(v -> LUtils.toast("提现成功"));
        etAmount.setError("大于账户余额，无法提现");
        dialog.show();
    }

}
