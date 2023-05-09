package com.rvutracker.controller;

import com.rvutracker.entity.CptCode;
import com.rvutracker.entity.Patient;
import com.rvutracker.persistence.GenericDao;
import com.rvutracker.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AddPatientTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao patientDao;

    /**
     * Creates the GenericDao and resets user database
     * with original content.
     */
    @BeforeEach
    void setUp() {

        patientDao = new GenericDao(Patient.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets charges from Add Patient form success.
     */
    @Test
    void getCharges() {
        AddPatient newPatient = new AddPatient();
        Map<CptCode, Integer> charges;

        charges = newPatient.getCharges("1", "3", "1", "2");
        logger.info(charges);

        assertEquals(4, charges.size());

    }
}