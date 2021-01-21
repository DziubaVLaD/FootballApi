package com.test.footballapi.data.model.mapper;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class BaseMapper {

    final static Locale LOCALE_US = Locale.US;
    final static SimpleDateFormat TIME_FORMAT_CLIENT = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private static SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.getDefault());


    Date parseDate(String date) {
        try {
            return SERVER_DATE_FORMAT.parse(date);
        } catch (Exception e) {
            return new Date();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}