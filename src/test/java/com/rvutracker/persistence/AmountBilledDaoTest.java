package com.rvutracker.persistence;

import com.rvutracker.entity.AmountBilled;
import com.rvutracker.entity.CptCode;
import com.rvutracker.entity.Patient;
import com.rvutracker.entity.User;
import com.rvutracker.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * The type Amount Billed dao test.
 */
public class AmountBilledDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao userDao;
    GenericDao patientDao;
    GenericDao amountBilledDao;
    GenericDao cptCodeDao;

    /**
     * Creates the GenericDao and resets amountbilled table
     * with original content.
     */
    @BeforeEach
    void setUp() {

        userDao = new GenericDao(User.class);
        patientDao = new GenericDao(Patient.class);
        amountBilledDao = new GenericDao(AmountBilled.class);
        cptCodeDao = new GenericDao(CptCode.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllBillingsSuccess() {
        List<AmountBilled> billings = amountBilledDao.getAll();
        assertEquals(3, billings.size());
    }

    /**
     * Gets patient by id success.
     */
    @Test
    void getPatientByIDSuccess() {
        Patient retrievedPatient = (Patient)patientDao.getById(1);
        assertEquals(2, retrievedPatient.getAmountBilled().size());
    }

    /**
     * Gets code by id success.
     */
    @Test
    void getCodeByIDSuccess() {
        CptCode cptCode = (CptCode)cptCodeDao.getById(1);
        assertEquals(96116, cptCode.getCode());
    }

    /**
     * Insert success.
     */
    @Test
    void insertSuccess() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Patient patientId = (Patient)patientDao.getById(2);
        CptCode codeId = (CptCode)cptCodeDao.getById(1);
        AmountBilled newBilling = new AmountBilled(1, timestamp, patientId,codeId);

        int id = amountBilledDao.insert(newBilling);

        AmountBilled insertedBilling = (AmountBilled) amountBilledDao.getById(id);
        AmountBilled retrievedBilling = (AmountBilled) amountBilledDao.getById(id);
        assertEquals(insertedBilling, retrievedBilling);
    }

    /**
     * Gets billing id by patientid and codeid.
     */
    @Test
    void getIDbyPatientCodeIds() {
        Patient patientId = new Patient();
        CptCode codeId = new CptCode();
        patientId.setId(1);
        codeId.setId(2);

        logger.info("The patient id: " + patientId.getId());
        logger.info("The code is: " + codeId.getId());

        List<AmountBilled> retrievedBilling = (List<AmountBilled>) amountBilledDao.getByPatientCodeIds(
                patientId.getId(), codeId.getId());
        logger.info("The retrieved billing is: " + retrievedBilling);
        assertEquals(1, retrievedBilling.size());

        int id = retrievedBilling.get(0).getId();
        assertEquals(2, id);
    }

    /**
     * Delete billing for patient by code success.
     */
    @Test
    void deleteBillingSuccess() {
        Patient patientId = new Patient();
        CptCode codeId = new CptCode();
        patientId.setId(1);
        codeId.setId(1);

        List<AmountBilled> retrievedBilling = (List<AmountBilled>) amountBilledDao.getByPatientCodeIds(
                patientId.getId(), codeId.getId());

        int id = retrievedBilling.get(0).getId();

        AmountBilled billingToDelete = (AmountBilled) amountBilledDao.getById(id);
        amountBilledDao.delete(billingToDelete);
        assertNull(amountBilledDao.getById(id));
    }

    /**
     * Update billing quantity success.
     */
    @Test
    void updateBillingSuccess() {
        int newQuantity = 2;
        AmountBilled amountBilled = (AmountBilled) amountBilledDao.getById(2);
        amountBilled.setQuantity(newQuantity);
        amountBilledDao.saveOrUpdate(amountBilled);
        AmountBilled retrievedBilling = (AmountBilled) amountBilledDao.getById(2);
        assertEquals(amountBilled, retrievedBilling);
    }



}
