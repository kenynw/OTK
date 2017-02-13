package com.miguan.otk.module.user;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.widget.EventDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        mTvSeriesDesc.setText(String.format(getString(R.string.text_sign_series_desc), 7 - sign.getIstoday()));
        mWvRules.loadData(sign.getDescription(), "text/html; charset=utf-8", null);

        if (sign.getCause() != null && sign.getCause().size() > 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
            List<CalendarDay> list = new ArrayList<>();
            for (int i=0; i<sign.getCause().size(); i++) {
                Sign.Cause cause = sign.getCause().get(i);
                try {
                    list.add(CalendarDay.from(format.parse(cause.getSign_in_date())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            mCalendarView.addDecorator(new EventDecorator(Color.RED, list));
        }

    }

    public void setBtnDisable() {
        mBtnSign.setEnabled(false);
    }
}
