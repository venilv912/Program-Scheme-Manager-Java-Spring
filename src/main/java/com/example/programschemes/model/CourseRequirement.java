package com.example.programschemes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"courserequirements\"")
@IdClass(CourseRequirementId.class)
public class CourseRequirement {

    @Id
    @Column(name = "scheme_id")
    private int schemeId;

    @Id
    @Column(name = "course_type_code")
    private String courseTypeCode;

    @Column(name = "program_id")
    private short programId;

    @Column(name = "min_course")
    private int minCourse;

    @Column(name = "max_course")
    private Integer maxCourse;

    @Column(name = "min_credits")
    private int minCredits;

    @Column(name = "max_credits")
    private Integer maxCredits;

    // Getters and Setters

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public String getCourseTypeCode() {
        return courseTypeCode;
    }

    public void setCourseTypeCode(String courseTypeCode) {
        this.courseTypeCode = courseTypeCode;
    }

    public short getProgramId() {
        return programId;
    }

    public void setProgramId(short programId) {
        this.programId = programId;
    }

    public int getMinCourse() {
        return minCourse;
    }

    public void setMinCourse(int minCourse) {
        this.minCourse = minCourse;
    }

    public Integer getMaxCourse() {
        return maxCourse;
    }

    public void setMaxCourse(Integer maxCourse) {
        this.maxCourse = maxCourse;
    }

    public int getMinCredits() {
        return minCredits;
    }

    public void setMinCredits(int minCredits) {
        this.minCredits = minCredits;
    }

    public Integer getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(Integer maxCredits) {
        this.maxCredits = maxCredits;
    }
}
