package com.rvutracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type AmountBilled
 *
 * @author Kue Xiong
 */
@Entity(name = "AmountBilled")
@Table(name = "amount_billed")
public class AmountBilled {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    //TODO: remaining variables
}
