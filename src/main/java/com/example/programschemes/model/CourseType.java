package com.example.programschemes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "coursetypes")
@IdClass(CourseTypeId.class)
public class CourseType {

    @Id
    @Column(name = "course_type_code")
    private String courseTypeCode;

    @Id
    @Column(name = "program_id")
    private short programId;

    @Column(name = "course_type_name", nullable = false)
    private String courseTypeName;

    @Column(name = "description")
    private String description;

    // Getters and Setters

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

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
