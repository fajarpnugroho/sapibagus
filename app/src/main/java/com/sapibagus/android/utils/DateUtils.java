package com.sapibagus.android.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class DateUtils {
    public DateUtils() {
    }

    public static long getDateInMillis(String srcDate) {
        try {
            // 2015-11-14 08:26:43
            @SuppressLint("SimpleDateFormat")
            Date date = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").parse(srcDate);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static CharSequence parseTimeAgo(String srcDate) {
        return android.text.format.DateUtils.getRelativeTimeSpanString(
                getDateInMillis(srcDate),
                System.currentTimeMillis(),
                android.text.format.DateUtils.MINUTE_IN_MILLIS,
                android.text.format.DateUtils.FORMAT_ABBREV_ALL
        );
    }
}
