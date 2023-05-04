package com.rvutracker.persistence;

import com.rvutracker.entity.Patient;
import com.rvutracker.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Report status.
 */
public class ReportStatus {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets report status count and puts them into a HashMap.
     *
     * @param user the user
     * @return the map
     */
    public Map<String, Integer> reportStatusCount(User user) {

        GenericDao userDao = new GenericDao(User.class);
        GenericDao patientDao = new GenericDao(Patient.class);

        Map<String, Integer> reportStatus = new HashMap<>();
        int inProgressCount = 0;
        int finalReviewCount = 0;
        int signedCount = 0;

        // Get all the patients for the user in session
        Set<Patient> patients = user.getPatients();
        logger.info("The patients for this user are: " + patients);

        // Add patient's report status to appropriate count type
        for (Patient patient : patients) {
            if (patient.getReportStatus().equals("In Progress")) {
                inProgressCount++;
            } else if (patient.getReportStatus().equals("Final Review")) {
                finalReviewCount++;
            } else if (patient.getReportStatus().equals("Signed")) {
                signedCount++;
            }
        }

        // Put count in hashmap
        reportStatus.put("In Progress", inProgressCount);
        reportStatus.put("Final Review", finalReviewCount);
        reportStatus.put("Signed", signedCount);

        return reportStatus;
    }
}
