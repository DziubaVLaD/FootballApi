package com.test.footballapi;

import com.test.footballapi.utils.CalculateDateInterval;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestCalculateDateInterval {
    @Test
    public void convertDateToString_isCorrect() {
        Date date = new Date();
        assertEquals(CalculateDateInterval.convertDateToString(date));
    }
}
