package com.test.footballapi;

import com.test.footballapi.utils.CalculateDateInterval;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestCalculateDateInterval {
    @Test
    public void calculateLastDay_isCorrect() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        assertEquals("2020-12-15", CalculateDateInterval.calculateLastDay("2020-12-15"));
        assertEquals(sdf.format(currentDate), CalculateDateInterval.calculateLastDay("2030-12-12"));
    }

    @Test
    public void calculateFirstDay_isCorrect() throws ParseException {
        assertEquals("2020-01-01", CalculateDateInterval.calculateFirstDay("2020-01-31"));
    }
}
