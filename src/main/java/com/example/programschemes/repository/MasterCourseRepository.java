package com.example.programschemes.repository;

import com.example.programschemes.model.MasterCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterCourseRepository extends JpaRepository<MasterCourse, Integer> {
    // custom queries
}
