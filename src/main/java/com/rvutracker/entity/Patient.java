package com.rvutracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Patient
 *
 * @author Kue Xiong
 */
@Entity(name = "Patient")
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "interviewDate")
    private String interviewDate;

    @Column(name = "referralQuestion")
    private String referralQuestion;

    @Column(name = "reportStatus")
    private String reportStatus;

    @ManyToOne
    @JoinColumn(name = "userId",
            foreignKey = @ForeignKey(name = "patient_user_id_fk"))
    private User user;

    @OneToMany(mappedBy = "patientid",cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AmountBilled> amountBilled = new LinkedHashSet<>();

    /**
     * Instantiates a new Patient
     */
    public Patient() {
    }

    /**
     * Instantiates a new Patient.
     *
     * @param firstName        the first name
     * @param lastName         the last name
     * @param interviewDate    the interview date
     * @param referralQuestion the referral question
     * @param reportStatus     the report status
     * @param user             the user
     */
    public Patient(String firstName, String lastName, String interviewDate,
                   String referralQuestion, String reportStatus, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.interviewDate = interviewDate;
        this.referralQuestion = referralQuestion;
        this.reportStatus = reportStatus;
        this.user = user;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets interview date.
     *
     * @return the interview date
     */
    public String getInterviewDate() {
        return interviewDate;
    }

    /**
     * Sets interview date.
     *
     * @param interviewDate the interview date
     */
    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    /**
     * Gets referral question.
     *
     * @return the referral question
     */
    public String getReferralQuestion() {
        return referralQuestion;
    }

    /**
     * Sets referral question.
     *
     * @param referralQuestion the referral question
     */
    public void setReferralQuestion(String referralQuestion) {
        this.referralQuestion = referralQuestion;
    }

    /**
     * Gets report status.
     *
     * @return the report status
     */
    public String getReportStatus() {
        return reportStatus;
    }

    /**
     * Sets report status.
     *
     * @param reportStatus the report status
     */
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets amount billed.
     *
     * @return the amount billed
     */
    public Set<AmountBilled> getAmountBilled() {
        return amountBilled;
    }

    /**
     * Sets amount billed.
     *
     * @param amountBilled the amount billed
     */
    public void setAmountBilled(Set<AmountBilled> amountBilled) {
        this.amountBilled = amountBilled;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", interviewDate='" + interviewDate + '\'' +
                ", referralQuestion='" + referralQuestion + '\'' +
                ", reportStatus=" + reportStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(interviewDate, patient.interviewDate) &&
                Objects.equals(referralQuestion, patient.referralQuestion) &&
                Objects.equals(reportStatus, patient.reportStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, interviewDate, referralQuestion, reportStatus);
    }
}
