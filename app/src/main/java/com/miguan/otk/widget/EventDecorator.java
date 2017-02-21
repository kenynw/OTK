package com.miguan.otk.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.miguan.otk.R;
import com.miguan.otk.model.bean.Sign;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Decorate several days with a dot
 */
public class EventDecorator implements DayViewDecorator {

    private Drawable mDrawable;

    private List<CalendarDay> dates;

    public EventDecorator(Context context, List<Sign.Cause> dates) {
        this.mDrawable = context.getResources().getDrawable(R.mipmap.ic_bg_signed);

        DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        this.dates = new ArrayList<>();
        for (int i=0; i<dates.size(); i++) {
            Sign.Cause cause = dates.get(i);
            try {
                this.dates.add(CalendarDay.from(format.parse(cause.getSign_in_date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
//        view.addSpan(new DotSpan(5, color));
        view.setBackgroundDrawable(mDrawable);
    }
}
