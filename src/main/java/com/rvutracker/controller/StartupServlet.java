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
        Properties cognitoProperties;
        logger.info("I am in the StartupServlet");

        try {
            quickChartProperties = loadProperties("/quickChart.properties");
            rvuProperties = loadProperties("/rvu.properties");
            cognitoProperties = loadProperties("/cognito.properties");

            logger.info("The QuickChart end point is: " + quickChartProperties.getProperty("url"));

            getQuickChartProperties(quickChartProperties);
            getRvuProperties(rvuProperties);
            getCognitoProperties(cognitoProperties);

        } catch (Exception e) {
            logger.error("There was an error: " + e.getStackTrace());
        }
    }

    /**
     * Gets quick chart properties and puts them into servlet context.
     *
     * @param quickChartProperties the quick chart properties
     */
    public void getQuickChartProperties(Properties quickChartProperties) {

        getServletContext().setAttribute("url", quickChartProperties.getProperty("url"));
        getServletContext().setAttribute("width", quickChartProperties.getProperty("width"));
        getServletContext().setAttribute("height", quickChartProperties.getProperty("height"));
        getServletContext().setAttribute("chartBeforeData", quickChartProperties.getProperty("chart.before.data"));
        getServletContext().setAttribute("chartAfterData", quickChartProperties.getProperty("chart.after.data"));
    }

    /**
     * Gets rvu properties and puts them into servlet context.
     *
     * @param rvuProperties the rvu properties
     */
    public void getRvuProperties(Properties rvuProperties) {

        getServletContext().setAttribute("targetRvu", rvuProperties.getProperty("target.rvu"));
    }

    /**
     * Gets cognito properties and puts them into servlet context.
     *
     * @param cognitoProperties the cognito properties
     */
    public void getCognitoProperties(Properties cognitoProperties) {

        getServletContext().setAttribute("CLIENT_ID", cognitoProperties.getProperty("client.id"));
        getServletContext().setAttribute("CLIENT_SECRET", cognitoProperties.getProperty("client.secret"));
        getServletContext().setAttribute("OAUTH_URL", cognitoProperties.getProperty("oauthURL"));
        getServletContext().setAttribute("LOGIN_URL", cognitoProperties.getProperty("loginURL"));
        getServletContext().setAttribute("LOGOUT_URL", cognitoProperties.getProperty("logoutURL"));
        getServletContext().setAttribute("REDIRECT_URL", cognitoProperties.getProperty("redirectURL"));
        getServletContext().setAttribute("REGION", cognitoProperties.getProperty("region"));
        getServletContext().setAttribute("POOL_ID", cognitoProperties.getProperty("poolId"));
    }
}
