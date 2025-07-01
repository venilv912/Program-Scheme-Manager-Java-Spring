package com.example.programschemes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mastercourses")
public class MasterCourse {

    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "canonical_title", nullable = false)
    private String canonicalTitle;

    @Column(name = "canonical_description")
    private String canonicalDescription;

    // Getters and Setters

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }

    public String getCanonicalDescription() {
        return canonicalDescription;
    }

    public void setCanonicalDescription(String canonicalDescription) {
        this.canonicalDescription = canonicalDescription;
    }
}
