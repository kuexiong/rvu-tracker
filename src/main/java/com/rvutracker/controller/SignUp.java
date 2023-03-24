package com.rvutracker.controller;

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
 * A servlet to sign up and create a user account.
 *
 * @author Kue Xiong
 */

@WebServlet(
        urlPatterns = {"/signUpServlet"}
)
public class SignUp extends HttpServlet {
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

        //Instantiate GenericDao of User object.
        GenericDao addNewUser = new GenericDao(User.class);

        // Get user information from HTML form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");

        // Instantiate new user.
        User user = new User(firstName, lastName, email, username);

        //Run method to insert into database.
        addNewUser.insert(user);

        // TODO: make this work
        // Store success message in session to display on JSP
        // when user account has been created.
        HttpSession session = request.getSession();

        String sessionMessage = "Account created. Return to login.";
        session.setAttribute("accountCreatedMessage", sessionMessage);

        // Redirect browser back to sign-in
        String url = "/index.jsp";
        response.sendRedirect(request.getContextPath() + url);
    }
}
