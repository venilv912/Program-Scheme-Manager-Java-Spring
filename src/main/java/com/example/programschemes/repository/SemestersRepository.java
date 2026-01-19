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

    @Query(value = "SELECT MAX(sm.STRID) FROM ec2.SEMESTERS sm", nativeQuery = true)
    Short findMaxSemesterId();
    @Query(value = "SELECT sm.STRID FROM ec2.SEMESTERS sm WHERE sm.STRROWSTATE > 0 AND sm.STRBCHID = :batchId AND sm.STRTRMID = :termId", nativeQuery = true)
    Long findSemesterId(@Param("batchId") Long batchId, @Param("termId") Long termId);
    @Query(value = """
            SELECT s.strid AS strid, CONCAT(p.prgname, ' - ', b.bchname) AS batch, CONCAT(t.trmname, ' (', a.ayrname, ')') AS term, s.strname AS semester FROM ec2.semesters AS s
            JOIN ec2.batches AS b
            ON s.strbchid=b.bchid
            JOIN ec2.programs AS p
            ON b.bchprgid=p.prgid
            JOIN ec2.terms AS t
            ON s.strtrmid=t.trmid
            JOIN ec2.academicyears AS a
            ON t.trmayrid=a.ayrid
            ORDER BY s.strid DESC
            """, nativeQuery = true)
    List<Object[]> getAllSemestersDetailsRaw();
}
