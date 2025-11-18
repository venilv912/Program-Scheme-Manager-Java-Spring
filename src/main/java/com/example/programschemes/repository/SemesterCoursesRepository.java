package com.example.programschemes.repository;

import com.example.programschemes.model.SemesterCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SemesterCoursesRepository extends JpaRepository<SemesterCourses, Integer> {

    // Fetch one semester course entry by SCRID
    @Query(value = "SELECT * FROM ec2.SEMESTERCOURSES scr " +
            "WHERE scr.SCRID = :scrid AND scr.SCRROWSTATE > 0",
            nativeQuery = true)
    SemesterCourses getByScrid(@Param("scrid") Long scrid);

    // Get all semester courses for a given semester (STRID)
    @Query(value = "SELECT * FROM ec2.SEMESTERCOURSES scr " +
            "WHERE scr.SCRSTRID = :semesterId AND scr.SCRROWSTATE > 0 " +
            "ORDER BY scr.SCRID",
            nativeQuery = true)
    List<SemesterCourses> getSemesterCourses(@Param("semesterId") Short semesterId);

    // Get all semester courses where elective = 'Y'
    @Query(value = "SELECT * FROM ec2.SEMESTERCOURSES scr " +
            "WHERE scr.SCRSTRID = :semesterId AND scr.SCRELECTIVE = 'Y' " +
            "AND scr.SCRROWSTATE > 0 " +
            "ORDER BY scr.SCRID",
            nativeQuery = true)
    List<SemesterCourses> getElectiveCourses(@Param("semesterId") Short semesterId);

    // Get all semester courses that are core (non-elective)
    @Query(value = "SELECT * FROM ec2.SEMESTERCOURSES scr " +
            "WHERE scr.SCRSTRID = :semesterId AND scr.SCRELECTIVE = 'N' " +
            "AND scr.SCRROWSTATE > 0 " +
            "ORDER BY scr.SCRID",
            nativeQuery = true)
    List<SemesterCourses> getCoreCourses(@Param("semesterId") Short semesterId);

    // Find SCRID using Course ID + STRID
    @Query(value = "SELECT scr.SCRID FROM ec2.SEMESTERCOURSES scr " +
            "WHERE scr.SCRCRSID = :crsId AND scr.SCRSTRID = :semesterId " +
            "AND scr.SCRROWSTATE > 0",
            nativeQuery = true)
    Long findScrid(@Param("crsId") Long crsId,
                   @Param("semesterId") Short semesterId);

    // Get max SCRID for manual ID generation
    @Query(value = "SELECT MAX(scr.SCRID) FROM ec2.SEMESTERCOURSES scr",
            nativeQuery = true)
    Long findMaxSemesterCourseid();

}
