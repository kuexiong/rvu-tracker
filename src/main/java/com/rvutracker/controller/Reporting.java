package com.rvutracker.controller;


import com.rvutracker.entity.User;
import com.rvutracker.persistence.CalculateRVU;
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
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Servlet that will display the user's RVUs for each month
 *
 * @author Kue Xiong
 */
@WebServlet(
        name = "reportingServlet",
        urlPatterns = { "/reportingServlet"}
)
public class Reporting extends HttpServlet {

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

        // Instantiate GenericDao of CalculateRVU object.
        GenericDao calculateRVU = new GenericDao(CalculateRVU.class);

        // TODO: activate when deploying to AWS
        // Get username from session
//        HttpSession session = request.getSession(false);
//
//        int sessionUser = (int)session.getAttribute("id");
//        logger.info("The id in session is: " + sessionUser);

        // Get user by id
//        User retrievedUser = (User)userDao.getById(sessionUser);
        User retrievedUser = (User)userDao.getById(8);
        logger.info("The retrieved user is: " + retrievedUser.getFirstName());

        // Get current month
        CalculateRVU getMonth = new CalculateRVU();
        Month month = getMonth.getCurrentMonth();
        String currentMonth = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        logger.info("The current month is: " + currentMonth);

        // Get current year
        CalculateRVU getYear = new CalculateRVU();
        int currentYear = getYear.getCurrentYear();
        logger.info("The current year is: " + currentYear);

        // Calculate RVUs
        CalculateRVU totals = new CalculateRVU();
        Map<String, Map<String, Float>> monthlyTotals = totals.calculate(8);
        logger.info("The monthly totals are: " + monthlyTotals);

        // Put monthly totals in a request
        request.setAttribute("currentMonth", currentMonth);
        request.setAttribute("currentYear", currentYear);
        request.setAttribute("monthlyTotals", monthlyTotals);
        request.setAttribute("monthYear", getMonthsYears(monthlyTotals));

        // Redirect browser to reporting page
        String url = "/reporting.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Gets months and its year within fiscal year.
     *
     * @param monthlyTotals the monthly totals
     * @return the months years
     */
    public Map<String, Integer> getMonthsYears(Map<String, Map<String, Float>> monthlyTotals) {

        Map<String, Integer> monthsYears = new HashMap<>();

        CalculateRVU fyFrom = new CalculateRVU();
        CalculateRVU fyTo = new CalculateRVU();
        int startYear = fyFrom.calculateFiscalFrom().get(Calendar.YEAR);
        int endYear = fyTo.calculateFiscalTo().get(Calendar.YEAR);

        logger.info("Start year is: " + startYear);
        logger.info("End year is: " + endYear);

        for (Map.Entry<String, Map<String, Float>> month : monthlyTotals.entrySet()) {
            logger.info("Hello!");
            logger.info("The month is: " + month.getKey());
            if (month.getKey().equals("July") || month.getKey().equals("August")
                    || month.getKey().equals("September") || month.getKey().equals("October")
                    || month.getKey().equals("November") || month.getKey().equals("December")) {
                monthsYears.put(month.getKey(), startYear);
            } else {
                monthsYears.put(month.getKey(), endYear);
            }
        }

        logger.info("Months and its years in fiscal year: " + monthsYears);

        return monthsYears;
    }
}
