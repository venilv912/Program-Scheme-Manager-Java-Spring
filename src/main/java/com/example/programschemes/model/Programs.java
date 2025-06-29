package com.example.programschemes.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Programs")
public class Programs {

    @Id
    @Column(name = "PRGID")
    private Short id;

    @Column(name = "PRGNAME", nullable = false)
    private String name;

    @Column(name = "PRGDESC", nullable = false)
    private String description;

    @Column(name = "PRGDURATION")
    private Short duration;

    @Column(name = "PRGDURATIONUNITS")
    private String durationUnits;

    @Column(name = "PRGINTRODATE")
    private Date introDate;

    @Column(name = "PRGFIELD1")
    private Short field1;

    @Column(name = "PRGFIELD2")
    private Short field2;

    @Column(name = "PRGFIELD3")
    private String field3;

    @Column(name = "PRGCREATEDBY")
    private Long createdBy;

    @Column(name = "PRGCREATEDAT", nullable = false)
    private Timestamp createdAt;

    @Column(name = "PRGLASTUPDATEDBY")
    private Long lastUpdatedBy;

    @Column(name = "PRGLASTUPDATEDAT")
    private Timestamp lastUpdatedAt;

    @Column(name = "PRGROWSTATE", nullable = false)
    private Short rowState;

    @Column(name = "PRGINSTCODE")
    private String instCode;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Scheme> schemes;

    public List<Scheme> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes) {
        this.schemes = schemes;
    }

    // Getters and Setters

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    public String getDurationUnits() {
        return durationUnits;
    }

    public void setDurationUnits(String durationUnits) {
        this.durationUnits = durationUnits;
    }

    public Date getIntroDate() {
        return introDate;
    }

    public void setIntroDate(Date introDate) {
        this.introDate = introDate;
    }

    public Short getField1() {
        return field1;
    }

    public void setField1(Short field1) {
        this.field1 = field1;
    }

    public Short getField2() {
        return field2;
    }

    public void setField2(Short field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Short getRowState() {
        return rowState;
    }

    public void setRowState(Short rowState) {
        this.rowState = rowState;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }
}
