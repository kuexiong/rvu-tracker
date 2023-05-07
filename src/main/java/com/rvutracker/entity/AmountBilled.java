package com.rvutracker.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * The type AmountBilled.
 *
 * @author Kue Xiong
 */
@Entity
@Table(name = "amountbilled")
public class AmountBilled {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
//    @Access(value = PROPERTY)
    private Integer id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "timestamp")
    private Timestamp timestamp;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "patientid")
    private Patient patientid;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "cptcodeid")
    private CptCode cptcodeid;

    /**
     * Instantiates a new Amount billed.
     */
    public AmountBilled() {
    }

    /**
     * Instantiates a new Amount billed.
     *
     * @param quantity  the quantity
     * @param timestamp the timestamp
     * @param patientid the patient id
     * @param cptcodeid the cpt code id
     */
    public AmountBilled(int quantity, Timestamp timestamp, Patient patientid, CptCode cptcodeid) {
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.patientid = patientid;
        this.cptcodeid = cptcodeid;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets patient id.
     *
     * @return the patient id
     */
    public Patient getPatientId() {
        return patientid;
    }

    /**
     * Sets patient id.
     *
     * @param patientId the patient id
     */
    public void setPatientId(Patient patientId) {
        this.patientid = patientId;
    }

    /**
     * Gets cpt code id.
     *
     * @return the cpt code id
     */
    public CptCode getCptCodeId() {
        return cptcodeid;
    }

    /**
     * Sets cpt code id.
     *
     * @param cptCodeId the cpt code id
     */
    public void setCptCodeId(CptCode cptCodeId) {
        this.cptcodeid = cptcodeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmountBilled that = (AmountBilled) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(patientid, that.patientid) &&
                Objects.equals(cptcodeid, that.cptcodeid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, timestamp, patientid, cptcodeid);
    }

    @Override
    public String toString() {
        return "AmountBilled{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ", patientId=" + patientid +
                ", cptCodeId=" + cptcodeid +
                '}';
    }
}
