package com.example.programschemes.model;

import java.io.Serializable;
import java.util.Objects;

public class SemesterCourseId implements Serializable {

    private int semNo;
    private int schemeId;
    private int courseSrNo;

    public SemesterCourseId() {}

    public SemesterCourseId(int semNo, int schemeId, int courseSrNo) {
        this.semNo = semNo;
        this.schemeId = schemeId;
        this.courseSrNo = courseSrNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SemesterCourseId)) return false;
        SemesterCourseId that = (SemesterCourseId) o;
        return semNo == that.semNo &&
                schemeId == that.schemeId &&
                courseSrNo == that.courseSrNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(semNo, schemeId, courseSrNo);
    }
}
