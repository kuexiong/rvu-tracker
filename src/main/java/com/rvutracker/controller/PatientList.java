package com.rvutracker.controller;


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

/**
 * Servlet that will display user's patients
 *
 * @author Kue Xiong
 */
@WebServlet(
        name = "patientListServlet",
        urlPatterns = { "/patientListServlet" }
)
public class PatientList extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles HTTP GET requests.
     *
     * @param request  Description of the Parameter
     * @param response Description of the Parameter
     * @exception ServletException if there is a Servlet failure
     * @exception IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate GenericDao of User object.
        GenericDao userDao = new GenericDao(User.class);

        //Instantiate GenericDao of Patient object.
//        GenericDao getPatients = new GenericDao(Patient.class);

        // TODO: get user from sign-in page
        // Get user by ID.
        User retrievedUser = (User)userDao.getById(1);
        logger.info("The retrieved user is: " + retrievedUser.getFirstName());

        //TODO: get patients

        // Get patients for user
//        retrievedUser.getPatients();

        // Place patients in session

        // Forward request and response to Patient List JSP
        String url = "/patientList.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
