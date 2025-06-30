package com.example.programschemes.repository;

import com.example.programschemes.model.CourseType;
import com.example.programschemes.model.CourseTypeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTypeRepository extends JpaRepository<CourseType, CourseTypeId> {
    List<CourseType> findByProgramId(short programId);
}
