package com.rvutracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


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
    private Date interviewDate;

    @Column(name = "referralQuestion")
    private String referralQuestion;

    //TODO: Many-to-one report status database

    @ManyToOne
    @JoinColumn(name = "userId",
            foreignKey = @ForeignKey(name = "patient_user_id_fk"))
    private User user;

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
     * @param user             the user
     */
    public Patient(String firstName, String lastName, Date interviewDate,
                   String referralQuestion, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.interviewDate = interviewDate;
        this.referralQuestion = referralQuestion;
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
    public Date getInterviewDate() {
        return interviewDate;
    }

    /**
     * Sets interview date.
     *
     * @param interviewDate the interview date
     */
    public void setInterviewDate(Date interviewDate) {
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

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", interviewDate=" + interviewDate +
                ", referralQuestion='" + referralQuestion + '\'' +
                ", user=" + user +
                '}';
    }
}
