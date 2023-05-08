package com.rvutracker.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = { "/logoutServlet"}
)
public class LogOut extends HttpServlet {

    public LogOut() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        if (session.getAttribute("id") != null) {
            session.removeAttribute("id");
        }

        String url = (String) context.getAttribute("LOGOUT_URL");

        response.sendRedirect(url);
    }
}
