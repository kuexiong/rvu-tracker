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

    private Map<String, Float> january;
    private Map<String, Float> february;
    private Map<String, Float> march;
    private Map<String, Float> april;
    private Map<String, Float> may;
    private Map<String, Float> june;
    private Map<String, Float> july;
    private Map<String, Float> august;
    private Map<String, Float> september;
    private Map<String, Float> october;
    private Map<String, Float> november;
    private Map<String, Float> december;
    private Map<String, Map<String, Float>> months;

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
    public void calculate() {

        initializeInstanceVariables();
        calculateFiscalYear();

        // Get all charges from database
        List<AmountBilled> allCharges = genericDao.getAll();
        logger.info("All the charges: " + allCharges);

        // Loop through array to add quantity and code to each month
        for(AmountBilled charge : allCharges) {

            float quantity = (float) charge.getQuantity();
            CptCode code = charge.getCptCodeId();
            String rvuValue = String.valueOf(code.getRvuValue());

            logger.info("The CPT code is " + rvuValue + " and the quantity is: " + quantity);

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
                    january.put(rvuValue, quantity);
                } else if (month == 2) {
                    february.put(rvuValue, quantity);
                } else if (month == 3) {
                    march.put(rvuValue, quantity);
                } else if (month == 4) {
                    april.put(rvuValue, quantity);
                } else if (month == 5) {
                    may.put(rvuValue, quantity);
                } else if (month == 6) {
                    june.put(rvuValue, quantity);
                } else if (month == 7) {
                    july.put(rvuValue, quantity);
                } else if (month == 8) {
                    august.put(rvuValue, quantity);
                } else if (month == 9) {
                    september.put(rvuValue, quantity);
                } else if (month == 10) {
                    october.put(rvuValue, quantity);
                } else if (month == 11) {
                    november.put(rvuValue, quantity);
                } else {
                    december.put(rvuValue, quantity);
                }
            }
        }

        addMonthlyChargesToArraylist();
        calculateMonthlyRVU();
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

        january = new LinkedHashMap<String, Float>();
        february = new LinkedHashMap<String, Float>();
        march = new LinkedHashMap<String, Float>();
        april = new LinkedHashMap<String, Float>();
        may = new LinkedHashMap<String, Float>();
        june = new LinkedHashMap<String, Float>();
        july = new LinkedHashMap<String, Float>();
        august = new LinkedHashMap<String, Float>();
        september = new LinkedHashMap<String, Float>();
        october = new LinkedHashMap<String, Float>();
        november = new LinkedHashMap<String, Float>();
        december = new LinkedHashMap<String, Float>();
    }

    /**
     * Add monthly arraylists of charges to main arraylist.
     */
    public void addMonthlyChargesToArraylist() {
        // Add each month of charges to arraylist
        months = new LinkedHashMap<String, Map<String, Float>>();

        months.put("January", january);
        months.put("February", february);
        months.put("March", march);
        months.put("April", april);
        months.put("May", may);
        months.put("June", june);
        months.put("July", july);
        months.put("August", august);
        months.put("September", september);
        months.put("October", october);
        months.put("November", november);
        months.put("December", december);

        // Remove months with no charges
        List<String> keysToRemove = new ArrayList();

        months.forEach((key, value) -> {
            if (value.isEmpty()) {
                keysToRemove.add(key);
            }
        });

        months.keySet().removeAll(keysToRemove);
        logger.info(months);
    }

    /**
     * Calculate total RVU for each month.
     */
    public void calculateMonthlyRVU() {

        float productOfCodeByQuantity = 0;
        float grandTotal = 0;

        for(Map.Entry<String, Map<String, Float>> month : months.entrySet()) {

            float monthlyTotal = 0;

            for (Map.Entry<String, Float> code : month.getValue().entrySet()) {

                float key = Float.valueOf(code.getKey());
                float value = code.getValue();

                productOfCodeByQuantity = key * value;
                logger.info("Product: " + productOfCodeByQuantity);

                monthlyTotal += productOfCodeByQuantity;
            }

            grandTotal += monthlyTotal;

            month.getValue().put("Total", monthlyTotal);
            logger.info("Monthly total: " + monthlyTotal);
            logger.info("Month w/Put: " + month);
            logger.info("The grand total is: " + grandTotal);

        }

        logger.info("All the months calculated: " + months);

        // TODO: return months for accessibility on JSP?
    }

}
