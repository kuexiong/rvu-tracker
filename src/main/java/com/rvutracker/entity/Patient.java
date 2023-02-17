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

    //TODO: constructors and setters and getters
}
