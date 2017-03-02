package com.miguan.otk.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.miguan.otk.R;
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
public class SignedDateDecorator implements DayViewDecorator {

    private Drawable mDrawable;

    private List<CalendarDay> dates;

    public SignedDateDecorator(Context context, String[] dates) {
        this.mDrawable = context.getResources().getDrawable(R.mipmap.ic_bg_signed);

        DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        this.dates = new ArrayList<>();
        for (String date : dates) {
            try {
                this.dates.add(CalendarDay.from(format.parse(date)));
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
