package com.miguan.otk.utils;

/**
 * Copyright (c) 2017/2/28. LiaoPeiKun Inc. All rights reserved.
 */

public class DateUtils {


    public static String getFormatTime(long time) {
        long hours = ((time % (1000 * 3600 * 24)) / (1000 * 3600));
        long minutes = ((time % (1000 * 3600)) / (1000 * 60));
        long seconds = (time % (1000 * 60) / 1000);
        return String.format("%1$02d:%2$02d:%3$02d", hours, minutes, seconds);
    }

    public static String getFormatDay(long time) {
        long days = (time / (1000 * 3600 * 24));
        long hours = ((time % (1000 * 3600 * 24)) / (1000 * 3600));
        long minutes = ((time % (1000 * 3600)) / (1000 * 60));
        long seconds = (time % (1000 * 60) / 1000);
        return String.format("%1$2d:%2$2d:%3$2d:%4$2d", days, hours, minutes, seconds);
    }

}
