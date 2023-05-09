package com.rvutracker.controller;

import com.rvutracker.entity.AmountBilled;
import com.rvutracker.persistence.GenericDao;
import com.rvutracker.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DisplayPatientInfoTest class.
 */
class DisplayPatientInfoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao genericDao;

    /**
     * Creates the GenericDao and resets user database
     * with original content.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(AmountBilled.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets 96116 in amountbilled table success.
     */
    @Test
    void get96116() {
        DisplayPatientInfo displayPatientInfo = new DisplayPatientInfo();

        String quantity = displayPatientInfo.get96116(1);
        assertEquals("1", quantity);
    }

    /**
     * Gets 96121 in amountbilled table success.
     */
    @Test
    void get96121() {
        DisplayPatientInfo displayPatientInfo = new DisplayPatientInfo();

        String quantity = displayPatientInfo.get96121(1);
        assertEquals("1", quantity);
    }

    /**
     * Gets 96132 in amountbilled table success.
     */
    @Test
    void get96132() {
        DisplayPatientInfo displayPatientInfo = new DisplayPatientInfo();

        String quantity = displayPatientInfo.get96116(1);
        assertEquals("1", quantity);
    }

    /**
     * Gets 96133 in amountbilled table success.
     */
    @Test
    void get96133() {
        DisplayPatientInfo displayPatientInfo = new DisplayPatientInfo();

        String quantity = displayPatientInfo.get96116(1);
        assertEquals("1", quantity);
    }
}