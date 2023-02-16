package com.rvutracker.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet to sign in to user's account.
 *
 * @author Kue Xiong
 */

@WebServlet(
        urlPatterns = {"/signInServlet"}
)
public class SignIn extends HttpServlet {
    /**
     * Handles HTTP GET requests
     *
     * @param request Description of the Parameter
     * @param response Description of the Parameter
     * @throws ServletException if there is a Servlet failure
     * @throws IOException if there is an IO failure
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Redirect browser back to sign-in
        String url = "/signInServlet";
        response.sendRedirect(request.getContextPath() + url);
    }

}

