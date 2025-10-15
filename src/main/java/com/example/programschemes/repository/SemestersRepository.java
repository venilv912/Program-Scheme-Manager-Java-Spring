package com.example.programschemes.repository;

import com.example.programschemes.model.Semesters;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SemestersRepository extends JpaRepository<Semesters, Short> {
    @Query(value = "SELECT * FROM ec2.SEMESTERS sm WHERE sm.STRROWSTATE > 0 AND sm.STRBCHID = :branchId ORDER BY sm.STRSEQNO", nativeQuery = true)
    List<Semesters> findActiveSemestersByBranchId(@Param("branchId") Short branchId);

    @Query(value = "SELECT MAX(sm.STRID) FROM ec2.SEMESTERS sm WHERE sm.STRROWSTATE > 0 AND sm.STRBCHID = :batchId", nativeQuery = true)
    Short findMaxSemesterId(@Param("batchId") Short batchId);

    @Query(value = "SELECT sm.STRID FROM ec2.SEMESTERS sm WHERE sm.STRROWSTATE > 0 AND sm.STRBCHID = :batchId AND sm.STRTRMID = :termId", nativeQuery = true)
    Long findSemesterId(@Param("batchId") Long batchId, @Param("termId") Long termId);

}
