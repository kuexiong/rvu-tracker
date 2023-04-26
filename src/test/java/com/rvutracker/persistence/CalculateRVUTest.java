package com.rvutracker.persistence;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CalculateRVUTest {

    @Test
    void getCurrentMonth() {
        CalculateRVU getMonth = new CalculateRVU();
        Month month = getMonth.getCurrentMonth();
        String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        assertEquals("April", monthName);
    }

    // TODO: assertion
    @Test
    void calculateFiscalYear() {
        CalculateRVU test = new CalculateRVU();
        test.calculateFiscalYear();
    }

    // TODO: assertion
    @Test
    void calculateSuccess() {
        CalculateRVU test = new CalculateRVU();
        test.calculate();
    }
}