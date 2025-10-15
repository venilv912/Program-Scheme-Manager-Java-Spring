package com.example.programschemes.repository;

import com.example.programschemes.model.Batches;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchesRepository extends JpaRepository<Batches, Short> {
    
    @Query(value = "SELECT * FROM ec2.BATCHES btc WHERE btc.BCHROWSTATE > 0 AND btc.BCHID = :batchId", nativeQuery = true)
    Batches getbtchId(@Param("batchId") Short batchId);

}
