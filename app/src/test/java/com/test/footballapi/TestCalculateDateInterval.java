package com.test.footballapi;

import com.test.footballapi.utils.CalculateDateInterval;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestCalculateDateInterval {
    @Test
    public void convertDateToString_isCorrect() throws ParseException {
        Date date = new SimpleDateFormat( "yyyy-MM-dd" ).parse( "2020-12-15" );
        assertEquals(CalculateDateInterval.convertDateToString(date), "2020-12-15");
    }
}
