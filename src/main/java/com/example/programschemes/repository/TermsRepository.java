package com.example.programschemes.repository;

import com.example.programschemes.model.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermsRepository extends JpaRepository<Terms, Short> {
    
    @Query(value = "SELECT * FROM ec2.TERMS trm,ec2.ACADEMICYEARS acdy WHERE trm.TRMROWSTATE > 0 AND acdy.AYRROWSTATE>0 AND trm.TRMAYRID = acdy.AYRID AND trm.TRMID = :termId", nativeQuery = true)
    Terms gettrmId(@Param("termId") Short termId);

    @Query(value = "SELECT trm.TRMID FROM ec2.TERMS trm,ec2.ACADEMICYEARS acdy WHERE trm.TRMROWSTATE > 0 AND acdy.AYRROWSTATE>0 AND trm.TRMAYRID = acdy.AYRID AND trm.TRMNAME = :name AND acdy.AYRID = :ayrid", nativeQuery = true)
    Long findTermIdByName(@Param("name") String name, @Param("ayrid") Long ayrid);
    @Query("SELECT MAX(t.trmid) FROM Terms t")
    Short findMaxTrmid();
    @Query(value = "SELECT t.trmid AS trmid, t.trmname AS trmname, a.ayrname AS ayrname, t.trm_starts AS trmstarts, t.trm_ends AS trmends FROM ec2.terms AS t JOIN ec2.academicyears AS a ON t.trmayrid=a.ayrid ORDER BY t.trmid DESC", nativeQuery = true)
    List<Object[]> getAllTermsDetailsRaw();
}
