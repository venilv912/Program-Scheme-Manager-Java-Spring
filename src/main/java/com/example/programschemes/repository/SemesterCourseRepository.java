package com.example.programschemes.repository;

import com.example.programschemes.model.SemesterCourse;
import com.example.programschemes.model.SemesterCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterCourseRepository extends JpaRepository<SemesterCourse, SemesterCourseId> {

    // Get all semester courses for a given scheme
    List<SemesterCourse> findBySchemeId(int schemeId);

    // Find a single course by composite key
    SemesterCourse findBySchemeIdAndSemNoAndCourseSrNo(int schemeId, int semNo, int courseSrNo);

    // Optional: get by scheme + semester number
    List<SemesterCourse> findBySchemeIdAndSemNo(int schemeId, int semNo);
    List<SemesterCourse> findBySchemeIdAndSemNoOrderByCourseSrNo(int schemeId, int semNo);
}
