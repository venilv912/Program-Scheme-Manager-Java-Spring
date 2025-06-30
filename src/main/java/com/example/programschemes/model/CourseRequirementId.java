package com.example.programschemes.model;

import java.io.Serializable;
import java.util.Objects;

public class CourseRequirementId implements Serializable {

    private int schemeId;
    private String courseTypeCode;

    public CourseRequirementId() {}

    public CourseRequirementId(int schemeId, String courseTypeCode) {
        this.schemeId = schemeId;
        this.courseTypeCode = courseTypeCode;
    }

    // equals and hashCode are required for composite keys

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseRequirementId)) return false;
        CourseRequirementId that = (CourseRequirementId) o;
        return schemeId == that.schemeId && Objects.equals(courseTypeCode, that.courseTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeId, courseTypeCode);
    }
}
