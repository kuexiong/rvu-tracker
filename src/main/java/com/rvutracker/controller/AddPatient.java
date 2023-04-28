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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A servlet to add a patient
 *
 * @author Kue Xiong
 */

@WebServlet(
        urlPatterns = {"/addPatientServlet"}
)
public class AddPatient extends HttpServlet {

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
        Patient patient = new Patient(firstName, lastName, dateOfInterview,
                 referralQuestion, reportStatus, retrievedUser);

        // Run method to insert into patient table
        int ptId = patientDao.insert(patient);

        // Insert charges into amountbilled table
        Patient patientId = (Patient)patientDao.getById(ptId);
        Map<CptCode, Integer> ptCharges = getCharges(cpt96116, cpt96121, cpt96132, cpt96133);

        for(Map.Entry<CptCode, Integer> entry : ptCharges.entrySet()) {
            CptCode cptCodeID = entry.getKey();
            int quantity = entry.getValue();

            AmountBilled newBilling = new AmountBilled(quantity, retrievedTimestamp, patientId, cptCodeID);
            amountBilledDao.insert(newBilling);
        }

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
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            logger.info(e.getStackTrace());
        }

        return timestamp;
    }

    /**
     * Gets charges from form and puts them into a Map.
     *
     * @param cpt96116 the cpt 96116
     * @param cpt96121 the cpt 96121
     * @param cpt96132 the cpt 96132
     * @param cpt96133 the cpt 96133
     * @return the charges
     */
    public Map<CptCode, Integer> getCharges(String cpt96116, String cpt96121,
                                            String cpt96132, String cpt96133) {

        Map<CptCode, Integer> charges = new HashMap<CptCode, Integer>();

        GenericDao cptCodeDao = new GenericDao(CptCode.class);
        List<CptCode> cptCodes = cptCodeDao.getAll();

        logger.info("All the cpt codes from table: " + cptCodes);

        if (!cpt96116.isEmpty()) {
            int codeId = cptCodes.get(0).getId();
            charges.put((CptCode) cptCodeDao.getById(codeId), Integer.parseInt(cpt96116));
        }
        if (!cpt96121.isEmpty()) {
            int codeId = cptCodes.get(1).getId();
            charges.put((CptCode) cptCodeDao.getById(codeId), Integer.parseInt(cpt96121));
        }
        if (!cpt96132.isEmpty()) {
            int codeId = cptCodes.get(2).getId();
            charges.put((CptCode) cptCodeDao.getById(codeId), Integer.parseInt(cpt96132));
        }
        if (!cpt96133.isEmpty()) {
            int codeId = cptCodes.get(2).getId();
            charges.put((CptCode) cptCodeDao.getById(codeId), Integer.parseInt(cpt96133));
        }

        logger.info("All the charges for the patient are (CodeID, quantity: " + charges);
        return charges;
    }
}
