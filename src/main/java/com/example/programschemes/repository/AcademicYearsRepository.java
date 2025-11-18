package com.example.programschemes.repository;
import com.example.programschemes.model.AcademicYears;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AcademicYearsRepository extends JpaRepository<AcademicYears, Short> {

    @Query(value = "SELECT ay.AYRID FROM ec2.ACADEMICYEARS ay WHERE ay.AYRNAME = :name AND ay.AYRROWSTATE > 0", nativeQuery = true)
    Long findAcademicYearIdByName(@Param("name") String name);
    @Query(value = "SELECT MAX(ayr.ayrid) FROM ec2.academicyears ayr", nativeQuery = true)
    Short findMaxAyrid();
    List<AcademicYears> findAllByOrderByAyridDesc();


}
