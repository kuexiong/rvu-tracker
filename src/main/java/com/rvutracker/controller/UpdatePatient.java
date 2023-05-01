package com.rvutracker.controller;

import com.rvutracker.entity.AmountBilled;
import com.rvutracker.entity.CptCode;
import com.rvutracker.entity.Patient;
import com.rvutracker.entity.User;
import com.rvutracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A servlet to add a patient
 *
 * @author Kue Xiong
 */

@WebServlet(
        urlPatterns = {"/updatePatientServlet"}
)
public class UpdatePatient extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * Handles HTTP POST requests
     *
     * @param request Description of the Parameter
     * @param response Description of the Parameter
     * @throws ServletException if there is a Servlet failure
     * @throws IOException if there is an IO failure
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Instantiate GenericDao of Patient object.
        GenericDao patientDao = new GenericDao(Patient.class);

        //Instantiate GenericDao of User object.
        GenericDao userDao = new GenericDao(User.class);
        GenericDao amountBilledDao = new GenericDao(AmountBilled.class);

        // Get patient information from HTML form.
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfInterview = request.getParameter("dateOfInterview");
        String referralQuestion = request.getParameter("referralQuestion");
        String reportStatus = request.getParameter("reportStatus");
        String cpt96116 = request.getParameter("96116quantity");
        String cpt96121 = request.getParameter("96121quantity");
        String cpt96132 = request.getParameter("96132quantity");
        String cpt96133 = request.getParameter("96133quantity");
        String timestamp = request.getParameter("timestamp");
        String update = request.getParameter("update");
        String delete = request.getParameter("delete");
        int id = Integer.parseInt(request.getParameter("patientId"));

        logger.info("The timestamp from form is: " + timestamp);

        Timestamp retrievedTimestamp = convertTimestamp(timestamp);

        logger.info("96116 quantity: " + cpt96116);
        logger.info("96121 quantity: " + cpt96121);
        logger.info("The timestamp is: " + retrievedTimestamp.getTime());

        // TODO: activate when deploying to AWS
        // Get username from session
//        HttpSession session = request.getSession(false);

//        int sessionUserId = (int)session.getAttribute("id");
//        logger.info("The id in session is: " + sessionUserId);

        // Get user by id
//        User retrievedUser = (User)userDao.getById(sessionUserId);
        User retrievedUser = (User)userDao.getById(8);
        logger.info("The retrieved user is: " + retrievedUser.getFirstName());

        // Instantiate new patient.
        Patient patient = (Patient)patientDao.getById(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setInterviewDate(dateOfInterview);
        patient.setReferralQuestion(referralQuestion);
        patient.setReportStatus(reportStatus);

        // Run method to insert into patient table
        patientDao.saveOrUpdate(patient);

        // Insert or update charges into amountbilled table
        insertUpdate(cpt96116, 0, patient, retrievedTimestamp);
        insertUpdate(cpt96121, 1, patient, retrievedTimestamp);
        insertUpdate(cpt96132, 2, patient, retrievedTimestamp);
        insertUpdate(cpt96133, 3, patient, retrievedTimestamp);

        // TODO: add success message

        // TODO: redirect browser back to patient list
        // Redirect browser back to add new patient
        String url = "/patientListServlet";
        response.sendRedirect(request.getContextPath() + url);
    }

    /**
     * Convert string timestamp to Timestamp type.
     *
     * @param retrievedTimestamp the retrieved timestamp
     * @return the timestamp
     */
    public Timestamp convertTimestamp(String retrievedTimestamp) {

        Timestamp timestamp = null;
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(retrievedTimestamp);
            timestamp = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            logger.info(e.getStackTrace());
        }

        return timestamp;
    }

    /**
     * Gets charges from form and insert or update in amountbilled table.
     *
     * @param cptQuantity    the cpt 96116
     * @param positionInList the position in list
     * @param patient        the patient
     * @param timestamp      the timestamp
     */
    public void insertUpdate(String cptQuantity, int positionInList, Patient patient, Timestamp timestamp) {

        GenericDao cptCodeDao = new GenericDao(CptCode.class);
        GenericDao amountBilledDao = new GenericDao(AmountBilled.class);
        List<AmountBilled> retrievedBilling;

        List<CptCode> cptCode = cptCodeDao.getAll();

        logger.info("All the cpt codes from table: " + cptCode);

        if (!cptQuantity.isEmpty()) {

            // Get id of cpt code
            int codeId = cptCode.get(positionInList).getId();
            CptCode code = (CptCode) cptCodeDao.getById(codeId);

            // Convert String quantity to integer
            int submittedQuantity = Integer.parseInt(cptQuantity);


            // Get billing by pt ID and cpt ID
            retrievedBilling = (List<AmountBilled>) amountBilledDao.getByPatientCodeIds(
                    patient.getId(), codeId);

            // If billing with pt ID and cpt ID exist
            if (!retrievedBilling.isEmpty()) {

                // Get billing entry
                AmountBilled billing = (AmountBilled) amountBilledDao.getById(retrievedBilling.get(0).getId());

                // Get the quantity from billing entry
                int quantity = billing.getQuantity();

                // If quantity from billing entry in table is different from quantity in form, update in table
                if (quantity != submittedQuantity) {
                    billing.setQuantity(submittedQuantity);
                    amountBilledDao.saveOrUpdate(billing);
                }
            } else {

                AmountBilled newBilling = new AmountBilled(submittedQuantity, timestamp, patient, code);
                amountBilledDao.insert(newBilling);
            }
        }
    }

    /**
     * Delete patient.
     *
     * @param patient the patient
     */
    public void deletePatient(Patient patient) {

        GenericDao patientDao = new GenericDao(Patient.class);

        patientDao.delete(patient);
    }
}
