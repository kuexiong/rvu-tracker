package com.rvutracker.controller;

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

/**
 * A servlet to display patient info in edit screen
 *
 * @author Kue Xiong
 */

@WebServlet(
        urlPatterns = {"/displayPatientInfoServlet"}
)
public class displayPatientInfo extends HttpServlet {

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

        //Instantiate GenericDao of Patient object.
        GenericDao patientDao = new GenericDao(Patient.class);

        //Instantiate GenericDao of User object.
        GenericDao userDao = new GenericDao(User.class);

        //TODO: get patient first name from JSP

        // Get patient information from Patient List.
        String firstName = request.getParameter("firstName");
        logger.info("First name clicked on Patient List is: " + firstName);

        // Run method to edit the patient
        Patient retrievedPatient = (Patient)patientDao.getByPropertyLike("firstName", firstName);
        logger.info("Retrieved patient is: " + retrievedPatient);

        // TODO: redirect browser to displayPatientInfoJSP
        // Redirect browser back to add new patient
        String url = "/patientList.jsp";
        response.sendRedirect(request.getContextPath() + url);
    }

}
