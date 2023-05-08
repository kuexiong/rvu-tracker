package com.rvutracker.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Calculate rvu test.
 */
class CalculateRVUTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets current month success.
     */
    @Test
    void getCurrentMonth() {
        CalculateRVU getMonth = new CalculateRVU();
        Month month = getMonth.getCurrentMonth();
        String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        assertEquals("May", monthName);
    }

    /**
     * Gets current year success.
     */
    @Test
    void getCurrentYear() {
        CalculateRVU getYear = new CalculateRVU();
        int currentYear = getYear.getCurrentYear();
        assertEquals(2023, currentYear);
    }

    /**
     * Calculate fiscal start success.
     */
    @Test
    void calculateFiscalStart() {
        CalculateRVU getStartYear = new CalculateRVU();
        Calendar fiscalStartYear = getStartYear.calculateFiscalStart();
        int year = (fiscalStartYear.get(Calendar.YEAR));
        assertEquals(2022, year);
    }

    /**
     * Calculate fiscal end success.
     */
    @Test
    void calculateFiscalEnd() {
        CalculateRVU getEndYear = new CalculateRVU();
        Calendar fiscalEndYear = getEndYear.calculateFiscalEnd();
        int year = (fiscalEndYear.get(Calendar.YEAR));
        assertEquals(2023, year);
    }


}