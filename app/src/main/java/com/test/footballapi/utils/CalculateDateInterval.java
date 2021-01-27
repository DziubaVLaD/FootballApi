package com.test.footballapi.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CalculateDateInterval {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final Date currentDate = new Date();

    public static String calculateLastDay(String endDateCompetition) throws ParseException {
        Date endDateCompetitionDate = sdf.parse(endDateCompetition);
        if (Objects.requireNonNull(endDateCompetitionDate).compareTo(currentDate) >= 0) {
            return convertDateToString(currentDate);
        } else {
            return convertDateToString(endDateCompetitionDate);
        }
    }

    public static String calculateFirstDay(String lastDayCompetition) throws ParseException {
        Date lastDayCompetitionDate = sdf.parse(lastDayCompetition);
        Calendar cal = Calendar.getInstance();
        cal.setTime(Objects.requireNonNull(lastDayCompetitionDate));
        cal.add(Calendar.DATE, -30);
        return sdf.format(cal.getTime());
    }

    private static String convertDateToString(Date date) {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        return df.format(date);
    }
}
