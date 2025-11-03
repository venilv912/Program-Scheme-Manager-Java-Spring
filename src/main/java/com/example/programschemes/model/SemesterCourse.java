package com.example.programschemes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "schemecourses")
@IdClass(SemesterCourseId.class)
public class SemesterCourse {

    @Id
    @Column(name = "sem_no")
    private int semNo;

    @Id
    @Column(name = "scheme_id")
    private int schemeId;

    @Id
    @Column(name = "course_sr_no")
    private int courseSrNo;

    @Column(name = "course_type_code")
    private String courseTypeCode;

    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "crsid")
    private Long crsid;
    @Column(name = "course_code") // length = 5
    private String courseCode;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "program_id")
    private short programId;

    @Column(name = "lecture_hours")
    private Integer lectureHours;

    @Column(name = "tutorial_hours")
    private Integer tutorialHours;

    @Column(name = "practical_hours")
    private Integer practicalHours;

    @Column(name = "total_credits")
    private Integer totalCredits;

    // Getters and Setters

    public int getSemNo() {
        return semNo;
    }

    public void setSemNo(int semNo) {
        this.semNo = semNo;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public int getCourseSrNo() {
        return courseSrNo;
    }

    public void setCourseSrNo(int courseSrNo) {
        this.courseSrNo = courseSrNo;
    }

    public String getCourseTypeCode() {
        return courseTypeCode;
    }

    public void setCourseTypeCode(String courseTypeCode) {
        this.courseTypeCode = courseTypeCode;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public short getProgramId() {
        return programId;
    }

    public void setProgramId(short programId) {
        this.programId = programId;
    }

    public Integer getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(Integer lectureHours) {
        this.lectureHours = lectureHours;
    }

    public Integer getTutorialHours() {
        return tutorialHours;
    }

    public void setTutorialHours(Integer tutorialHours) {
        this.tutorialHours = tutorialHours;
    }

    public Integer getPracticalHours() {
        return practicalHours;
    }

    public void setPracticalHours(Integer practicalHours) {
        this.practicalHours = practicalHours;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }
    public Long getCrsid() { return crsid; }
    public void setCrsid(Long crsid) { this.crsid = crsid; }

}
