package com.rvutracker.controller;

import com.rvutracker.entity.Patient;
import com.rvutracker.entity.User;
import com.rvutracker.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet to add a patient
 *
 * @author Kue Xiong
 */

@WebServlet(
        urlPatterns = {"/addPatientServlet"}
)
public class AddPatient extends HttpServlet {
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
        GenericDao addNewPatient = new GenericDao(Patient.class);

        //Instantiate GenericDao of User object.
        GenericDao userDao = new GenericDao(User.class);

        // Get patient information from HTML form.
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfInterview = request.getParameter("dateOfInterview");
        String referralQuestion = request.getParameter("referralQuestion");
        String reportStatus = request.getParameter("reportStatus");

        // TODO: get user from sign-in page
        // Get user by ID.
        User retrievedUser = (User)userDao.getById(2);

        // Instantiate new patient.
        Patient patient = new Patient(firstName, lastName, dateOfInterview,
                 referralQuestion, reportStatus, retrievedUser);

        // Run method to insert into database
        addNewPatient.insert(patient);

        // TODO: add success message

        // TODO: redirect browser back to patient list
        // Redirect browser back to add new patient
        String url = "/addNewPatient.jsp";
        response.sendRedirect(request.getContextPath() + url);
    }
}
