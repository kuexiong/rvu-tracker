package com.rvutracker.controller;

import com.rvutracker.entity.AmountBilled;
import com.rvutracker.entity.Patient;
import com.rvutracker.entity.User;
import com.rvutracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * A servlet to display patient info in edit screen
 *
 * @author Kue Xiong
 */

@WebServlet(
        urlPatterns = {"/displayPatientInfoServlet"}
)
public class DisplayPatientInfo extends HttpServlet {

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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate GenericDao of Patient object.
        GenericDao patientDao = new GenericDao(Patient.class);

        // Instantiate GenericDao of User object.
        GenericDao userDao = new GenericDao(User.class);

        // Get patient ID from Patient List
        String id = request.getParameter("patientId");
        int patientId = Integer.parseInt(id);
        logger.info("The selected patient is: " + patientDao.getById(patientId));

        // Retrieve patient from patient table
        Patient retrievedPatient = (Patient) patientDao.getById(patientId);

        // Retrieve quantity of each CPT code for patient
        String quantity96116 = get96116(patientId);
        String quantity96121 = get96121(patientId);
        String quantity96132 = get96132(patientId);
        String quantity96133 = get96133(patientId);

        // Put patient in a request
        request.setAttribute("patient", retrievedPatient);
        request.setAttribute("quantity96116", quantity96116);
        request.setAttribute("quantity96121", quantity96121);
        request.setAttribute("quantity96132", quantity96132);
        request.setAttribute("quantity96133", quantity96133);

        // Redirect browser back to edit patient form
        String url = "/editPatient.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Gets quantity for 96116.
     *
     * @param patientId the patient id
     * @return the 96116
     */
    public String get96116(int patientId) {

        // Instantiate GenericDao of AmountBilled object.
        GenericDao amountBilledDao = new GenericDao(AmountBilled.class);

        String quantity = null;

        List<AmountBilled> cpt96116 = (List<AmountBilled>)amountBilledDao.getByPatientCodeIds(patientId, 1);

        if (!cpt96116.isEmpty()) {
            quantity = String.valueOf(((AmountBilled) amountBilledDao.getById(cpt96116.get(0).getId())).getQuantity());
        }

        logger.info("96116 quantity for patient is: " + quantity);

        return quantity;
    }

    /**
     * Gets quantity for 96121.
     *
     * @param patientId the patient id
     * @return the 96121
     */
    public String get96121(int patientId) {

        // Instantiate GenericDao of AmountBilled object.
        GenericDao amountBilledDao = new GenericDao(AmountBilled.class);

        String quantity = null;

        List<AmountBilled> cpt96121 = (List<AmountBilled>)amountBilledDao.getByPatientCodeIds(patientId, 2);

        if (!cpt96121.isEmpty()) {
            quantity = String.valueOf(((AmountBilled)amountBilledDao.getById(cpt96121.get(0).getId())).getQuantity());
        }

        logger.info("96121 quantity for patient is: " + quantity);

        return quantity;
    }

    /**
     * Gets quantity for 96132.
     *
     * @param patientId the patient id
     * @return the 96132
     */
    public String get96132(int patientId) {

        // Instantiate GenericDao of AmountBilled object.
        GenericDao amountBilledDao = new GenericDao(AmountBilled.class);

        String quantity = null;

        List<AmountBilled> cpt96132 = (List<AmountBilled>)amountBilledDao.getByPatientCodeIds(patientId, 3);

        if (!cpt96132.isEmpty()) {
            quantity = String.valueOf(((AmountBilled)amountBilledDao.getById(cpt96132.get(0).getId())).getQuantity());
        }

        logger.info("96132 quantity for patient is: " + quantity);

        return quantity;
    }

    /**
     * Gets quantity for 96133.
     *
     * @param patientId the patient id
     * @return the 96133
     */
    public String get96133(int patientId) {

        // Instantiate GenericDao of AmountBilled object.
        GenericDao amountBilledDao = new GenericDao(AmountBilled.class);

//        int quantity = 0;
        String quantity = null;

        List<AmountBilled> cpt96133 = (List<AmountBilled>)amountBilledDao.getByPatientCodeIds(patientId, 4);

        if (!cpt96133.isEmpty()){
            quantity = String.valueOf(((AmountBilled)amountBilledDao.getById(cpt96133.get(0).getId())).getQuantity());
        }

        logger.info("96133 quantity for patient is: " + quantity);

        return quantity;
    }

}
