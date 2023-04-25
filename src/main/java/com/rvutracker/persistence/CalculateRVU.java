package com.rvutracker.persistence;

import com.rvutracker.entity.AmountBilled;
import com.rvutracker.entity.CptCode;
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

    private Map<Integer, Integer> january;
    private Map<Integer, Integer> february;
    private Map<Integer, Integer> march;
    private Map<Integer, Integer> april;
    private Map<Integer, Integer> may;
    private Map<Integer, Integer> june;
    private Map<Integer, Integer> july;
    private Map<Integer, Integer> august;
    private Map<Integer, Integer> september;
    private Map<Integer, Integer> october;
    private Map<Integer, Integer> november;
    private Map<Integer, Integer> december;
    private List<Map<Integer, Integer>> months;

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

            int quantity = charge.getQuantity();
            CptCode code = charge.getCptCodeId();
            int cptCode = code.getCode();

            logger.info("The CPT code is " + cptCode + " and the quantity is: " + quantity);

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
                    january.put(cptCode, quantity);
                } else if (month == 2) {
                    february.put(cptCode, quantity);
                } else if (month == 3) {
                    march.put(cptCode, quantity);
                } else if (month == 4) {
                    april.put(cptCode, quantity);
                } else if (month == 5) {
                    may.put(cptCode, quantity);
                } else if (month == 6) {
                    june.put(cptCode, quantity);
                } else if (month == 7) {
                    july.put(cptCode, quantity);
                } else if (month == 8) {
                    august.put(cptCode, quantity);
                } else if (month == 9) {
                    september.put(cptCode, quantity);
                } else if (month == 10) {
                    october.put(cptCode, quantity);
                } else if (month == 11) {
                    november.put(cptCode, quantity);
                } else {
                    december.put(cptCode, quantity);
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

        january = new LinkedHashMap<Integer, Integer>();
        february = new LinkedHashMap<Integer, Integer>();
        march = new LinkedHashMap<Integer, Integer>();
        april = new LinkedHashMap<Integer, Integer>();
        may = new LinkedHashMap<Integer, Integer>();
        june = new LinkedHashMap<Integer, Integer>();
        july = new LinkedHashMap<Integer, Integer>();
        august = new LinkedHashMap<Integer, Integer>();
        september = new LinkedHashMap<Integer, Integer>();
        october = new LinkedHashMap<Integer, Integer>();
        november = new LinkedHashMap<Integer, Integer>();
        december = new LinkedHashMap<Integer, Integer>();
    }

    /**
     * Add monthly arraylists of charges to main arraylist.
     */
    public void addMonthlyChargesToArraylist() {
        // Add each month of charges to arraylist
        months = new ArrayList<Map<Integer, Integer>>();
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
