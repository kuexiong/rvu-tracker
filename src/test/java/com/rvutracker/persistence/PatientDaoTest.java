package com.rvutracker.persistence;

import com.rvutracker.entity.Patient;
import com.rvutracker.entity.User;
import com.rvutracker.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Patient dao test.
 */
class PatientDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao genericDao;

    /**
     * Creates the GenericDao and resets patient database
     * with original content.
     */
    @BeforeEach
    void setUp() {

        genericDao = new GenericDao(Patient.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all patients success.
     */
    @Test
    void getAllPatientsSuccess() {
        List<Patient> patients = genericDao.getAll();
        assertEquals(4,patients.size());
    }

    /**
     * Verifies gets by id success.
     */
    @Test
    void getByIdSuccess() {
        Patient retrievedPatient = (Patient)genericDao.getById(1);
        assertNotNull(retrievedPatient);
        assertEquals(retrievedPatient, genericDao.getById(1));
    }

    /**
     * Verifies save or update success.
     */
    @Test
    void saveOrUpdate() {
        String newReferralQuestion = "Epilepsy";
        Patient patient = (Patient)genericDao.getById(3);
        patient.setReferralQuestion(newReferralQuestion);
        genericDao.saveOrUpdate(patient);
        Patient retrievedPatient = (Patient)genericDao.getById(3);
        assertEquals(patient,retrievedPatient);
    }

    /**
     * Verifies insert new patient success.
     */
    @Test
    void insertSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(2);
        Patient newPatient = new Patient("Suzy","Sheep", "2023-01-30","Learning difficulties","Signed", user);
        user.addPatient(newPatient);

        int id = genericDao.insert(newPatient);
        assertNotEquals(0,id);

        Patient insertedPatient = (Patient)genericDao.getById(id);
        Patient retrievedPatient = (Patient)genericDao.getById(id);
        assertEquals(insertedPatient,retrievedPatient);
    }

    /**
     * Verifies delete a patient success.
     */
    @Test
    void deleteSuccess() {
        Patient patientToBeDeleted = (Patient)genericDao.getById(2);
        genericDao.delete(patientToBeDeleted);
        assertNull(genericDao.getById(2));
    }

    /**
     * Verifies get by property like success.
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Patient> retrievedPatients = genericDao.getByPropertyLike("reportStatus","Final");
        assertEquals(1, retrievedPatients.size());
    }
}