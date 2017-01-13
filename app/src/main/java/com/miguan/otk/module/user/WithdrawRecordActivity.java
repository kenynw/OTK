package com.miguan.otk.module.user;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Withdraw;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(WithdrawRecordPresenter.class)
public class WithdrawRecordActivity extends BaseDataActivity<WithdrawRecordPresenter, Withdraw> {

    @Bind(R.id.tv_cash_record_balance)
    TextView mTvBalance;

    @Bind(R.id.tv_cash_record_account)
    TextView mTvAccount;

    @Bind(R.id.btn_cash_record_withdraw)
    Button mBtnWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_take_cash);
        ButterKnife.bind(this);

        mBtnWithdraw.setOnClickListener(v -> showCashDialog());
    }

    @Override
    public void setData(Withdraw withdraw) {
        mTvBalance.setText(withdraw.getMoney());
        mTvAccount.setText(withdraw.getAlipay_account());
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
