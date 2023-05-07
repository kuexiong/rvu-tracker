package com.rvutracker.controller;

import com.rvutracker.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

@WebServlet(
        name = "startupServlet",
        urlPatterns = { "/startupServlet"},
        loadOnStartup = 1
)
public class StartupServlet extends HttpServlet implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void init() {

        Properties quickChartProperties;
        Properties rvuProperties;
        logger.info("I am in the StartupServlet");

        try {
            quickChartProperties = loadProperties("/quickChart.properties");
            rvuProperties = loadProperties("/rvu.properties");
            logger.info("The QuickChart end point is: " + quickChartProperties.getProperty("url"));

            getServletContext().setAttribute("url", quickChartProperties.getProperty("url"));
            getServletContext().setAttribute("width", quickChartProperties.getProperty("width"));
            getServletContext().setAttribute("height", quickChartProperties.getProperty("height"));
            getServletContext().setAttribute("chartBeforeData", quickChartProperties.getProperty("chart.before.data"));
            getServletContext().setAttribute("chartAfterData", quickChartProperties.getProperty("chart.after.data"));
            getServletContext().setAttribute("targetRvu", rvuProperties.getProperty("target.rvu"));

        } catch (Exception e) {
            logger.error("There was an error: " + e.getStackTrace());
        }
    }
}
