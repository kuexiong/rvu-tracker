package com.rvutracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent the status of a report.
 * Unidirectional 1:* relationship
 *
 * @author Kue Xiong
 */
@Entity(name = "ReportStatus")
@Table(name = "report_status")
public class ReportStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    private String description;

    @OneToMany(mappedBy = "report_status", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ReportStatus> reportStatuses = new HashSet<>();

    private Patient patient;

    /**
     * Instantiates a new Report status.
     */
    public ReportStatus() {
    }

    /**
     * Instantiates a new Report status.
     *
     * @param description the description
     */
    public ReportStatus(String description) {
        this.description = description;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReportStatus{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportStatus that = (ReportStatus) o;
        return id == that.id &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
