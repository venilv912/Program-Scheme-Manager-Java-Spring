package com.example.programschemes.repository;

import com.example.programschemes.model.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SchemeRepository extends JpaRepository<Scheme, Integer> {

    // This is the method to add:
    List<Scheme> findByProgram_Id(Short programId);

}
