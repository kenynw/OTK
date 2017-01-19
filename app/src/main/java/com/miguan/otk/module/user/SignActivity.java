package com.miguan.otk.module.user;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Sign;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SignPresenter.class)
public class SignActivity extends BaseDataActivity<SignPresenter, Sign> {

    @Bind(R.id.tv_sign_series_desc)
    TextView mTvSeriesDesc;

    @Bind(R.id.calendarView)
    CalendarView mCalendarView;

    @Bind(R.id.btn_sign_now)
    Button mBtnSign;

    @Bind(R.id.wv_sign_rules)
    WebView mWvRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_sign_in);
        setToolbarTitle(R.string.title_activity_sign_in);
        ButterKnife.bind(this);

        Calendar   cal=Calendar.getInstance();//获取当前日期
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH,1);
//        mCalendarView.setDate(System.currentTimeMillis());
        mCalendarView.setMinDate(cal.getTimeInMillis());
        mCalendarView.setMaxDate(System.currentTimeMillis());
        mCalendarView.setFirstDayOfWeek(2);

    }

    @Override
    public void setData(Sign sign) {
        if (sign.getIsqiandao() == 1) {
            mBtnSign.setEnabled(false);
        } else {
            mBtnSign.setOnClickListener(v -> getPresenter().sign());
        }
        mTvSeriesDesc.setText(String.format(getString(R.string.text_sign_series_desc), 7 - sign.getIstoday()));
        mWvRules.loadData(sign.getDescription(), "text/html; charset=utf-8", null);
    }

    public void setBtnDisable() {
        mBtnSign.setEnabled(false);
    }
}
