package com.example.programschemes.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Scheme")
public class Scheme {

    @Id
    @Column(name = "scheme_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Programs program;

    @Column(name = "effective_from_year", nullable = false)
    private Integer effectiveFromYear;

    @Column(name = "max_credit_load")
    private Integer maxCreditLoad;

    @Column(name = "max_courses")
    private Integer maxCourses;

    @Column(name = "min_cpi", precision = 4, scale = 2, nullable = false)
    private BigDecimal minCpi;

    @Column(name = "min_credits", nullable = false)
    private Integer minCredits;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Programs getProgram() {
        return program;
    }

    public void setProgram(Programs program) {
        this.program = program;
    }

    public Integer getEffectiveFromYear() {
        return effectiveFromYear;
    }

    public void setEffectiveFromYear(Integer effectiveFromYear) {
        this.effectiveFromYear = effectiveFromYear;
    }

    public Integer getMaxCreditLoad() {
        return maxCreditLoad;
    }

    public void setMaxCreditLoad(Integer maxCreditLoad) {
        this.maxCreditLoad = maxCreditLoad;
    }

    public Integer getMaxCourses() {
        return maxCourses;
    }

    public void setMaxCourses(Integer maxCourses) {
        this.maxCourses = maxCourses;
    }

    public BigDecimal getMinCpi() {
        return minCpi;
    }

    public void setMinCpi(BigDecimal minCpi) {
        this.minCpi = minCpi;
    }

    public Integer getMinCredits() {
        return minCredits;
    }

    public void setMinCredits(Integer minCredits) {
        this.minCredits = minCredits;
    }
}
