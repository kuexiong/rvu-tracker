package com.rvutracker.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CalculateRVUTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void getCurrentMonth() {
        CalculateRVU getMonth = new CalculateRVU();
        Month month = getMonth.getCurrentMonth();
        String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        assertEquals("May", monthName);
    }

    @Test
    void getCurrentYear() {
        CalculateRVU getYear = new CalculateRVU();
        int currentYear = getYear.getCurrentYear();
        assertEquals(2023, currentYear);
    }

//    // TODO: assertion
//    @Test
//    void calculateFiscalYear() {
//        CalculateRVU test = new CalculateRVU();
//        test.calculateFiscalYear();
//    }

    // TODO: assertion
    @Test
    void calculateSuccess() {
        CalculateRVU test = new CalculateRVU();
        test.calculate(1);
    }
}