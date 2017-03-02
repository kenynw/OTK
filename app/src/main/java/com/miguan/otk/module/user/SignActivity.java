package com.miguan.otk.module.user;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.widget.SignedDateDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SignPresenter.class)
public class SignActivity extends BaseDataActivity<SignPresenter, Sign> {

    @Bind(R.id.tv_sign_series_desc)
    TextView mTvSeriesDesc;

    @Bind(R.id.calendarView)
    MaterialCalendarView mCalendarView;

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

        Calendar cal = Calendar.getInstance(); //获取当前日期
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH,1);
        mCalendarView.setOnMonthChangedListener(getPresenter());
        mCalendarView.state().edit()
                .setMinimumDate(cal)
                .setMaximumDate(Calendar.getInstance())
                .commit();
    }

    @Override
    public void setData(Sign sign) {
        if (sign.getIsqiandao() == 1) {
            mBtnSign.setEnabled(false);
            mBtnSign.setText("已签到");
        } else {
            mBtnSign.setOnClickListener(v -> getPresenter().sign());
        }
        SpannableString span = new SpannableString(String.format(getString(R.string.text_sign_series_desc), 7 - sign.getIstoday()));
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#fab02e")), 13, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvSeriesDesc.setText(span);
        mWvRules.loadData(sign.getDescription(), "text/html; charset=utf-8", null);
        setCalendar(sign);
    }

    public void setCalendar(Sign sign) {
        if (sign.getCause() != null && sign.getCause().length > 0) {
            mCalendarView.addDecorator(new SignedDateDecorator(this, sign.getCause()));
        }
    }

    public void setBtnDisable() {
        mBtnSign.setEnabled(false);
        mBtnSign.setText("已签到");
    }
}
