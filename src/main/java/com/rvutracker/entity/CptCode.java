package com.rvutracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//TODO: need to unit test
/**
 * The type CptCode
 *
 * @author Kue Xiong
 */
@Entity(name = "CptCode")
@Table(name = "cpt_code")
public class CptCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "cptCode")
    private int cptCode;

    @Column(name = "cptCodeDescription")
    private String cptCodeDescription;

    @Column(name = "rvuValue")
    private float rvuValue;

    @OneToMany(mappedBy = "cpt_code", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AmountBilled> amountBilled = new HashSet<>();

    /**
     * Instantiates a new CptCode
     */
    public CptCode(){
    }

    /**
     * Instantiates a new Cpt code.
     *
     * @param id                 the id
     * @param cptCode            the cpt code
     * @param cptCodeDescription the cpt code description
     * @param rvuValue           the rvu value
     */
    public CptCode(int id, int cptCode, String cptCodeDescription, float rvuValue) {
        this.id = id;
        this.cptCode = cptCode;
        this.cptCodeDescription = cptCodeDescription;
        this.rvuValue = rvuValue;
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
     * Gets cpt code.
     *
     * @return the cpt code
     */
    public int getCptCode() {
        return cptCode;
    }

    /**
     * Gets cpt code description.
     *
     * @return the cpt code description
     */
    public String getCptCodeDescription() {
        return cptCodeDescription;
    }

    /**
     * Gets rvu value.
     *
     * @return the rvu value
     */
    public float getRvuValue() {
        return rvuValue;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CptCode cptCode1 = (CptCode) o;
        return id == cptCode1.id &&
                cptCode == cptCode1.cptCode &&
                Float.compare(cptCode1.rvuValue, rvuValue) == 0 &&
                Objects.equals(cptCodeDescription, cptCode1.cptCodeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cptCode, cptCodeDescription, rvuValue);
    }

    @Override
    public String toString() {
        return "CptCode{" +
                "id=" + id +
                ", cptCode=" + cptCode +
                ", cptCodeDescription='" + cptCodeDescription + '\'' +
                ", rvuValue=" + rvuValue +
                '}';
    }
}
