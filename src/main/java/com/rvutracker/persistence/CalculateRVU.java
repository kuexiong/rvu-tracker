package com.rvutracker.persistence;

import com.rvutracker.entity.AmountBilled;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class CalculateRVU {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao genericDao;
    private Month currentMonth;
    private Calendar fiscalYearFrom;
    private Calendar fiscalYearTo;
    private Calendar cal;
    private int month;

    private Set<AmountBilled> january;
    private Set<AmountBilled> february;
    private Set<AmountBilled> march;
    private Set<AmountBilled> april;
    private Set<AmountBilled> may;
    private Set<AmountBilled> june;
    private Set<AmountBilled> july;
    private Set<AmountBilled> august;
    private Set<AmountBilled> september;
    private Set<AmountBilled> october;
    private Set<AmountBilled> november;
    private Set<AmountBilled> december;
    private List<Set<AmountBilled>> months;

    /**
     * Gets current month.
     *
     * @return the current month
     */
    public Month getCurrentMonth() {
        LocalDate currentDate = LocalDate.now();

        currentMonth = currentDate.getMonth();
        logger.info("The current month is: " + currentMonth);
        return currentMonth;
    }

    /**
     * Gets charges for each month and puts into arraylists.
     */
    public void getMonthlyCharges() {

        initializeInstanceVariables();
        calculateFiscalYear();

        // Get all charges from database
        List<AmountBilled> allCharges = genericDao.getAll();
        logger.info("All the charges: " + allCharges);

        // Loop through array to add quantity and code to each month
        for(AmountBilled charge : allCharges) {
            Timestamp timestamp = charge.getTimestamp();
            cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp.getTime()));
            logger.info(cal.getTime());
            month = (cal.get(Calendar.MONTH) + 1);
            int year = cal.get(Calendar.YEAR);

            logger.info("The month from timestamp is: " + month);
            logger.info("The year from timestamp is: " + year);

            // if the date is greater than June 2022 and less than July 2023
            if (cal.after(fiscalYearFrom) && cal.before(fiscalYearTo)) {
                logger.info("Got into the if statement");
                if (month == 1) {
                    january.add(charge);
                } else if (month == 2) {
                    february.add(charge);
                } else if (month == 3) {
                    march.add(charge);
                } else if (month == 4) {
                    april.add(charge);
                } else if (month == 5) {
                    may.add(charge);
                } else if (month == 6) {
                    june.add(charge);
                } else if (month == 7) {
                    july.add(charge);
                } else if (month == 8) {
                    august.add(charge);
                } else if (month == 9) {
                    september.add(charge);
                } else if (month == 10) {
                    october.add(charge);
                } else if (month == 11) {
                    november.add(charge);
                } else {
                    december.add(charge);
                }
            }
        }

        addMonthlyChargesToArraylist();
    }

    /**
     * Calculate fiscal year.
     */
    public void calculateFiscalYear() {

        int fyFrom;
        int fyTo;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);

        if (currentMonth < 7) {
            fyFrom = (currentYear - 1);
            fyTo = (currentYear);
        } else {
            fyFrom = currentYear;
            fyTo = (currentYear + 1);
        }

        //fiscal year: July 2022 - June 2023
        fiscalYearFrom = Calendar.getInstance();
        fiscalYearFrom.set(fyFrom, (6), 1, 0, 0);

        fiscalYearTo = Calendar.getInstance();
        fiscalYearTo.set(fyTo, 5, 30, 23, 59, 59);

        logger.info(fiscalYearFrom.getTime());
        logger.info(fiscalYearTo.getTime());
    }

    /**
     * Initialize instance variables.
     */
    public void initializeInstanceVariables() {
        genericDao = new GenericDao(AmountBilled.class);

        january = new LinkedHashSet<AmountBilled>();
        february = new LinkedHashSet<AmountBilled>();
        march = new LinkedHashSet<AmountBilled>();
        april = new LinkedHashSet<AmountBilled>();
        may = new LinkedHashSet<AmountBilled>();
        june = new LinkedHashSet<AmountBilled>();
        july = new LinkedHashSet<AmountBilled>();
        august = new LinkedHashSet<AmountBilled>();
        september = new LinkedHashSet<AmountBilled>();
        october = new LinkedHashSet<AmountBilled>();
        november = new LinkedHashSet<AmountBilled>();
        december = new LinkedHashSet<AmountBilled>();
    }

    /**
     * Add monthly arraylists of charges to main arraylist.
     */
    public void addMonthlyChargesToArraylist() {
        // Add each month of charges to arraylist
        months = new ArrayList<Set<AmountBilled>>();
        months.add(january);
        months.add(february);
        months.add(march);
        months.add(april);
        months.add(may);
        months.add(june);
        months.add(july);
        months.add(august);
        months.add(september);
        months.add(october);
        months.add(november);
        months.add(december);

        // Remove months with no charges
        months.removeIf(element -> element.isEmpty());
        logger.info(months);
    }

    //TODO: Sum each CPT code type and multiply by RVU value
        // loop through list and add cpt code type to count
        // if 96116, then 96116 ++
        // etc
    //TODO: Total up the product of count x RVU value

}
