package com.example.programschemes.repository;

import com.example.programschemes.model.Batches;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchesRepository extends JpaRepository<Batches, Short> {
    
    @Query(value = "SELECT * FROM ec2.BATCHES btc WHERE btc.BCHROWSTATE > 0 AND btc.BCHID = :batchId", nativeQuery = true)
    Batches getbtchId(@Param("batchId") Short batchId);
    @Query("SELECT MAX(b.bchid) FROM Batches b")
    Integer findMaxBatchId();
    List<Batches> findAllByOrderByBchidDesc();
    @Query(value = "SELECT b.bchid as bchid, a.ayrname as ayrname, p.prgname as prgname, b.bchname as bchname, b.bchcapacity as bchcapacity, s.effective_from_year as schemeyear FROM ec2.batches AS b JOIN ec2.academicyears AS a ON b.bchcalid=a.ayrid JOIN ec2.programs AS p ON b.bchprgid=p.prgid LEFT JOIN ec2.scheme AS s ON b.scheme_id=s.scheme_id ORDER BY b.bchid DESC", nativeQuery = true)
    List<Object[]> getAllBatchesDetailsRaw();
}
