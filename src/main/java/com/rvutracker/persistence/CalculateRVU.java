package com.rvutracker.persistence;

import com.rvutracker.entity.AmountBilled;
import com.rvutracker.entity.CptCode;
import com.rvutracker.entity.Patient;
import com.rvutracker.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * The type CalculateRVU.
 */
public class CalculateRVU {

    private final Logger logger = LogManager.getLogger(this.getClass());
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

    /**
     * Gets current month.
     *
     * @return the current month
     */
    public Month getCurrentMonth() {
        Month currentMonth;
        LocalDate currentDate = LocalDate.now();

        currentMonth = currentDate.getMonth();
        return currentMonth;
    }

    /**
     * Gets current year.
     *
     * @return the current year
     */
    public int getCurrentYear() {
        int currentYear;
        LocalDate currentDate = LocalDate.now();

        currentYear = currentDate.getYear();
        return currentYear;
    }

    /**
     * Gets charges for each month and puts into arraylists.
     */
    public Map<String, Map<String, Float>> calculate(int userId) {

        int month;
        Calendar cal;
        GenericDao amountBilledDao = new GenericDao(AmountBilled.class);
        GenericDao userDao = new GenericDao(User.class);

        initializeInstanceVariables();

        // Get all the patients for the user in session
        User user = (User) userDao.getById(userId);
        Set<Patient> patients = user.getPatients();
        logger.info("The patients for this user are: " + patients);

        List<AmountBilled> allCharges = new ArrayList<>();

        List<AmountBilled> patientBilling = new ArrayList<>();

        // Put billing that has patient's ID in allCharges arraylist
        for (Patient patient : patients) {
            int patientId = patient.getId();
            logger.info("The patient ID is: " + patientId);
            if (patients.stream().anyMatch(pt -> patientId == pt.getId()) == true) {
                patientBilling = amountBilledDao.getByPatientId(patientId);
                allCharges.addAll(patientBilling);
            }
        }

        // Loop through array of charges to add quantity and code to each month
        for(AmountBilled charge : allCharges) {

            float quantity = (float) charge.getQuantity();
            CptCode code = charge.getCptCodeId();
            String rvuValue = String.valueOf(code.getRvuValue());

            logger.info("The CPT code is " + rvuValue + " and the quantity is: " + quantity);

            // Convert timestamp to Calendar date
            Timestamp timestamp = charge.getTimestamp();
            cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp.getTime()));
            logger.info("Timestamp: " + cal.getTime());
            month = (cal.get(Calendar.MONTH) + 1);

            logger.info("The month from timestamp is: " + month);

            // if the date is greater than June 2022 and less than July 2023
            if (cal.after(calculateFiscalStart()) && cal.before(calculateFiscalEnd())) {

                if (month == 1) {
                    addToQuantity(january, rvuValue,quantity);
                } else if (month == 2) {
                    addToQuantity(february, rvuValue,quantity);
                } else if (month == 3) {
                    addToQuantity(march, rvuValue,quantity);
                } else if (month == 4) {
                    addToQuantity(april, rvuValue,quantity);
                } else if (month == 5) {
                    addToQuantity(may, rvuValue,quantity);
                } else if (month == 6) {
                    addToQuantity(june, rvuValue,quantity);
                } else if (month == 7) {
                    addToQuantity(july, rvuValue,quantity);
                } else if (month == 8) {
                    addToQuantity(august, rvuValue,quantity);
                } else if (month == 9) {
                    addToQuantity(september, rvuValue,quantity);
                } else if (month == 10) {
                    addToQuantity(october, rvuValue,quantity);
                } else if (month == 11) {
                    addToQuantity(november, rvuValue,quantity);
                } else {
                    addToQuantity(december, rvuValue,quantity);
                }
            }

        }

        Map<String, Map<String, Float>>monthlyTotals = new LinkedHashMap<String, Map<String, Float>>();
        monthlyTotals = calculateMonthlyRVU(addMonthlyChargesToArraylist());
        return monthlyTotals;
    }

    /**
     * Calculate start of fiscal year.
     *
     * @return
     */
    public Calendar calculateFiscalStart() {

        Calendar fiscalYearStart;
        int fyFrom;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);

        if (currentMonth < 7) {
            fyFrom = (currentYear - 1);
        } else {
            fyFrom = currentYear;
        }

        //fiscal year: July 2022 - June 2023
        fiscalYearStart = Calendar.getInstance();
        fiscalYearStart.set(fyFrom, (6), 1, 0, 0);


        logger.info(fiscalYearStart.getTime());

        return fiscalYearStart;
    }

    /**
     * Calculate end of fiscal year.
     */
    public Calendar calculateFiscalEnd() {

        Calendar fiscalYearEnd;
        int fyTo;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);

        if (currentMonth < 7) {
            fyTo = (currentYear);
        } else {
            fyTo = (currentYear + 1);
        }

        fiscalYearEnd = Calendar.getInstance();
        fiscalYearEnd.set(fyTo, 5, 30, 23, 59, 59);

        logger.info(fiscalYearEnd.getTime());

        return fiscalYearEnd;
    }

    /**
     * Initialize instance variables.
     */
    public void initializeInstanceVariables() {
        GenericDao genericDao;
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
    public Map<String, Map<String, Float>> addMonthlyChargesToArraylist() {

        Map<String, Map<String, Float>> months;
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

        return months;
    }

    /**
     * Calculate total RVU for each month.
     */
    public Map<String, Map<String, Float>> calculateMonthlyRVU(Map<String, Map<String, Float>> months) {

        float productOfCodeByQuantity = 0;

        for(Map.Entry<String, Map<String, Float>> month : months.entrySet()) {

            float monthlyTotal = 0;

            for (Map.Entry<String, Float> code : month.getValue().entrySet()) {

                float key = Float.valueOf(code.getKey());
                float value = code.getValue();

                productOfCodeByQuantity = key * value;
                logger.info("Product: " + productOfCodeByQuantity);

                monthlyTotal += productOfCodeByQuantity;
            }

            month.getValue().put("Total", monthlyTotal);
            logger.info("Monthly total: " + monthlyTotal);
            logger.info("Month w/Put: " + month);

        }

        logger.info("All the months calculated: " + months);

        return months;
    }

    /**
     * Add to quantity of each rvuValue in Map.
     *
     * @param month    the month
     * @param rvuValue the rvu value
     * @param quantity the quantity
     */
    public void addToQuantity(Map<String, Float> month, String rvuValue, float quantity) {

        if (month.containsKey(rvuValue)) {
            month.put(rvuValue, month.get(rvuValue) + quantity);
        } else {
            month.put(rvuValue, quantity);
        }
    }


}
