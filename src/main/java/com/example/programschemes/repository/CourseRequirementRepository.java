package com.example.programschemes.repository;

import com.example.programschemes.model.CourseRequirement;
import com.example.programschemes.model.CourseRequirementId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRequirementRepository extends JpaRepository<CourseRequirement, CourseRequirementId> {

    // Find all course requirements for a given scheme
    List<CourseRequirement> findBySchemeId(int schemeId);

    // Optionally: Find by scheme + course type
    CourseRequirement findBySchemeIdAndCourseTypeCode(int schemeId, String courseTypeCode);

    List<CourseRequirement> findByProgramIdAndSchemeId(short programId, int schemeId);
}
