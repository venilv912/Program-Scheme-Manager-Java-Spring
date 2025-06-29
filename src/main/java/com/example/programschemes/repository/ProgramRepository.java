package com.example.programschemes.repository;

import com.example.programschemes.model.Programs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Programs, Short> {
}
