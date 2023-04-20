package com.rvutracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

import static javax.persistence.AccessType.PROPERTY;

/**
 * The type AmountBilled.
 *
 * @author Kue Xiong
 */
@Entity
@Table(name = "amount_billed")
public class AmountBilled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billingId", nullable = false)
    @Access(value = PROPERTY)
    private Integer id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "timeStamp")
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "patientId")
    private Patient patientId;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "cptCodeId")
    private CptCode cptCodeId;

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
     * @param patientId the patient id
     * @param cptCodeId the cpt code id
     */
    public AmountBilled(int quantity, Timestamp timestamp, Patient patientId, CptCode cptCodeId) {
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.patientId = patientId;
        this.cptCodeId = cptCodeId;
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
        return patientId;
    }

    /**
     * Sets patient id.
     *
     * @param patientId the patient id
     */
    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets cpt code id.
     *
     * @return the cpt code id
     */
    public CptCode getCptCodeId() {
        return cptCodeId;
    }

    /**
     * Sets cpt code id.
     *
     * @param cptCodeId the cpt code id
     */
    public void setCptCodeId(CptCode cptCodeId) {
        this.cptCodeId = cptCodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmountBilled that = (AmountBilled) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(cptCodeId, that.cptCodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, timestamp, patientId, cptCodeId);
    }

    @Override
    public String toString() {
        return "AmountBilled{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ", patientId=" + patientId +
                ", cptCodeId=" + cptCodeId +
                '}';
    }
}
