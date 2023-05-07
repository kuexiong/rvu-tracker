package com.rvutracker.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type CptCode for cptCode table,
 * which is a reference table.
 *
 * @author Kue Xiong
 */
@Entity(name = "CptCode")
@Table(name = "cptcode")
public class CptCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "code")
    private int code;

    @Column(name = "codedescription")
    private String codeDescription;

    @Column(name = "rvuvalue")
    private float rvuValue;

    @OneToMany(mappedBy = "cptcodeid", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AmountBilled> amountBilled = new LinkedHashSet<>();

    /**
     * Instantiates a new CptCode
     */
    public CptCode(){
    }

    /**
     * Instantiates a new Cpt code.
     *
     * @param code            the cpt code
     * @param codeDescription the cpt code description
     * @param rvuValue           the rvu value
     */
    public CptCode(int code, String codeDescription, float rvuValue) {
        this.code = code;
        this.codeDescription = codeDescription;
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets cpt code.
     *
     * @return the cpt code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets cpt code description.
     *
     * @return the cpt code description
     */
    public String getCodeDescription() {
        return codeDescription;
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
                code == cptCode1.code &&
                Float.compare(cptCode1.rvuValue, rvuValue) == 0 &&
                Objects.equals(codeDescription, cptCode1.codeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, codeDescription, rvuValue);
    }

    @Override
    public String toString() {
        return "CptCode{" +
                "id=" + id +
                ", cptCode=" + code +
                ", cptCodeDescription='" + codeDescription + '\'' +
                ", rvuValue=" + rvuValue +
                '}';
    }
}
