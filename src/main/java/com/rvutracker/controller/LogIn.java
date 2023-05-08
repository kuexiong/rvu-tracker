package com.rvutracker.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/logIn"}
)

/** Begins the authentication process using AWS Cognito
 *
 */
public class LogIn extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = getServletContext();

        String url = context.getAttribute("LOGIN_URL") + "?response_type=code&client_id="
                + context.getAttribute("CLIENT_ID") + "&redirect_uri="
                + context.getAttribute("REDIRECT_URL");
        resp.sendRedirect(url);
    }
}