package com.example.android.retrofittoppops.database.utils;

import com.example.android.retrofittoppops.utils.Const;

import java.util.Date;

public class DateCompare {
    public static boolean isSameDay (Date date1, Date date2) {

        long day1 = date1.getTime() / Const.DateConstant.MILLIS_IN_DAY;
        long day2 = date2.getTime() / Const.DateConstant.MILLIS_IN_DAY;

        return  day1 == day2;
    }
}
