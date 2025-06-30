package com.example.programschemes.model;

import java.io.Serializable;
import java.util.Objects;

public class CourseTypeId implements Serializable {
    private String courseTypeCode;
    private short programId;

    public CourseTypeId() {}

    public CourseTypeId(String courseTypeCode, short programId) {
        this.courseTypeCode = courseTypeCode;
        this.programId = programId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseTypeId)) return false;
        CourseTypeId that = (CourseTypeId) o;
        return programId == that.programId &&
                Objects.equals(courseTypeCode, that.courseTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTypeCode, programId);
    }
}
