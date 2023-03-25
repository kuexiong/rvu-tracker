package com.rvutracker.controller;


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
import javax.servlet.http.HttpSession;
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

        // Get username from session
        HttpSession session = request.getSession(false);

        int sessionUser = (int)session.getAttribute("id");
        logger.info("The id in session is: " + sessionUser);

        // Get user by id
        User retrievedUser = (User)userDao.getById(sessionUser);
        logger.info("The retrieved user is: " + retrievedUser.getFirstName());

        // Get patients for user and puts it in a session
        request.setAttribute("patients", retrievedUser.getPatients());
        logger.info("List of patients for user: " + retrievedUser.getPatients());

        // Forward request and response to Patient List JSP
        String url = "/patientList.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
