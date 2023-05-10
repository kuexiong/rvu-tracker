package com.rvutracker.controller;


import com.rvutracker.entity.User;
import com.rvutracker.persistence.CalculateRVU;
import com.rvutracker.persistence.GenericDao;
import com.rvutracker.util.PropertiesLoader;
import io.quickchart.QuickChart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class Reporting extends HttpServlet implements PropertiesLoader {

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

        //instantiate servletcontext object
        ServletContext context = getServletContext();

        // Instantiate GenericDao of User object.
        GenericDao userDao = new GenericDao(User.class);

        // Instantiate GenericDao of CalculateRVU object.
        GenericDao calculateRVU = new GenericDao(CalculateRVU.class);


        // Get username from session
        HttpSession session = request.getSession(false);

        int sessionUser = (int)session.getAttribute("id");
        logger.info("The id in session is: " + sessionUser);

        // Get user by id
        User retrievedUser = (User)userDao.getById(sessionUser);
//        User retrievedUser = (User)userDao.getById(8);
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
        Map<String, Map<String, Float>> monthlyTotals = totals.calculate(sessionUser);
        logger.info("The monthly totals are: " + monthlyTotals);

        // Get current RUV for the month
        Map<String, Float> currentMonthTotal = getCurrentMonthTotal(monthlyTotals, currentMonth);

        // Remove current month from Map
        monthlyTotals.remove(currentMonth);
        logger.info(currentMonth + " has been removed from " + monthlyTotals);

        // Get properties from servlet context
        int targetRvu = Integer.parseInt((String) context.getAttribute("targetRvu"));
        int width = Integer.parseInt((String) context.getAttribute("width"));
        int height = Integer.parseInt((String) context.getAttribute("height"));
        String beforeData = (String) context.getAttribute("chartBeforeData");
        String afterData = (String) context.getAttribute("chartAfterData");
        logger.info("The rvu target from properties file is: " + targetRvu);

        float currentTotal = currentMonthTotal.get("Total");
        logger.info("--------------- QuickChart--------------\n");
        logger.info("The current total for the month is: " + currentMonthTotal);
        logger.info("--------------- QuickChart--------------\n");

        // Calculate percentage
        int percentage = calculatePercentage(currentTotal, targetRvu);
        logger.info("The calculated percentage is: " + percentage);

        // Get progress bar image URL
        String progressBarUrl = getProgressBar(beforeData, afterData, width, height, percentage);

        // Put monthly totals in a request
        request.setAttribute("currentMonth", currentMonth);
        request.setAttribute("currentYear", currentYear);
        request.setAttribute("currentMonthlyTotal", currentMonthTotal);
        request.setAttribute("monthlyTotals", monthlyTotals);
        request.setAttribute("monthYear", getMonthsYears(monthlyTotals));
        request.setAttribute("progressBar", progressBarUrl);

        // Forward request to progressBarServlet to get progress bar
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
        int startYear = fyFrom.calculateFiscalStart().get(Calendar.YEAR);
        int endYear = fyTo.calculateFiscalEnd().get(Calendar.YEAR);

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

    /**
     * Gets current month total.
     *
     * @param monthlyTotals the monthly totals
     * @param currentMonth  the current month
     * @return the current month total
     */
    public Map<String, Float> getCurrentMonthTotal(Map<String, Map<String, Float>> monthlyTotals,
                                                                String currentMonth) {

        Map<String, Float> currentMonthTotal = new HashMap<>();

        for (Map.Entry<String, Map<String, Float>> entry : monthlyTotals.entrySet()) {
            logger.info("The entry.getKey() is: " + entry.getKey());
            if (entry.getKey().equals(currentMonth)) {

                logger.info("The entry.getValue() is: " + entry.getValue());

                currentMonthTotal.putAll(entry.getValue());
            }
        }
        logger.info("The total for the current month is: " + currentMonthTotal);

        return currentMonthTotal;
    }

    /**
     * Gets progress bar.
     *
     * @param beforeData the before data
     * @param afterData  the after data
     * @param width      the width
     * @param height     the height
     * @param percentage the percentage
     * @return the progress bar
     */
    public String getProgressBar(String beforeData, String afterData,
                                 int width, int height, int percentage) {

        String config = beforeData + percentage + afterData;
        logger.info("The data config string is: " + config);

        QuickChart chart = new QuickChart();
        chart.setWidth(width);
        chart.setHeight(height);
        chart.setConfig(config);

        logger.info("The complete QuickChart url is: " + chart.getUrl());
        return chart.getUrl();
    }

    /**
     * Calculate percentage (current total/monthly target).
     *
     * @param current the current
     * @param target  the target
     * @return the int
     */
    public int calculatePercentage(float current, int target) {

        int currentInInt = (int) current;

        return currentInInt * 100 / target;
    }
}
