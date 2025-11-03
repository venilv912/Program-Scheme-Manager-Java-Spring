package com.example.programschemes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "termcourses", schema = "ec2")
public class TermCourses {

    @Id
    @Column(name = "tcrid")
    private Long tcrid;

    @Column(name = "tcrtrmid")
    private Short tcrtrmid = 0;

    @Column(name = "tcrcrsid")
    private Long tcrcrsid = 0L;

    @Column(name = "tcrtype", length = 10)
    private String tcrtype = "N"; // e.g. Normal / Core / Elective etc.

    @Column(name = "tcrfacultyid")
    private Long tcrfacultyid = 0L;

    @Column(name = "tcrroundlogic", length = 10)
    private String tcrroundlogic = "N";

    @Column(name = "tcrmarks")
    private Short tcrmarks = 0;

    @Column(name = "tcrstatus", length = 1)
    private String tcrstatus = "A"; // Active (1-char status)

    @Column(name = "tcr_access_status", length = 1)
    private String tcrAccessStatus = "C"; // Closed / Completed (1-char)

    @Column(name = "tcrcreatedby")
    private Long tcrcreatedby = 0L;

    @Column(name = "tcrcreatedat")
    private LocalDateTime tcrcreatedat = LocalDateTime.now();

    @Column(name = "tcrlastupdatedby")
    private Long tcrlastupdatedby = 0L;

    @Column(name = "tcrlastupdatedat")
    private LocalDateTime tcrlastupdatedat = LocalDateTime.now();

    @Column(name = "tcrrowstate")
    private Integer tcrrowstate = 1; // 1 = Active, 0 = Inactive

    // Automatically fill defaults before inserting into DB
    @PrePersist
    public void prePersist() {
        if (this.tcrtrmid == null) this.tcrtrmid = 0;
        if (this.tcrcrsid == null) this.tcrcrsid = 0L;
        if (this.tcrtype == null) this.tcrtype = "N";
        if (this.tcrfacultyid == null) this.tcrfacultyid = 0L;
        if (this.tcrroundlogic == null) this.tcrroundlogic = "N";
        if (this.tcrmarks == null) this.tcrmarks = 0;
        if (this.tcrstatus == null || this.tcrstatus.length() != 1) this.tcrstatus = "A";
        if (this.tcrAccessStatus == null || this.tcrAccessStatus.length() != 1) this.tcrAccessStatus = "C";
        if (this.tcrcreatedby == null) this.tcrcreatedby = 0L;
        if (this.tcrlastupdatedby == null) this.tcrlastupdatedby = 0L;
        if (this.tcrcreatedat == null) this.tcrcreatedat = LocalDateTime.now();
        if (this.tcrlastupdatedat == null) this.tcrlastupdatedat = LocalDateTime.now();
        if (this.tcrrowstate == null) this.tcrrowstate = 1;
    }

    // Getters and Setters
    public Long getTcrid() { return tcrid; }
    public void setTcrid(Long tcrid) { this.tcrid = tcrid; }

    public Short getTcrtrmid() { return tcrtrmid; }
    public void setTcrtrmid(Short tcrtrmid) { this.tcrtrmid = tcrtrmid; }

    public Long getTcrcrsid() { return tcrcrsid; }
    public void setTcrcrsid(Long tcrcrsid) { this.tcrcrsid = tcrcrsid; }

    public String getTcrtype() { return tcrtype; }
    public void setTcrtype(String tcrtype) { this.tcrtype = tcrtype; }

    public Long getTcrfacultyid() { return tcrfacultyid; }
    public void setTcrfacultyid(Long tcrfacultyid) { this.tcrfacultyid = tcrfacultyid; }

    public String getTcrroundlogic() { return tcrroundlogic; }
    public void setTcrroundlogic(String tcrroundlogic) { this.tcrroundlogic = tcrroundlogic; }

    public Short getTcrmarks() { return tcrmarks; }
    public void setTcrmarks(Short tcrmarks) { this.tcrmarks = tcrmarks; }

    public String getTcrstatus() { return tcrstatus; }
    public void setTcrstatus(String tcrstatus) { this.tcrstatus = tcrstatus; }

    public String getTcraccessstatus() { return tcrAccessStatus; }
    public void setTcraccessstatus(String tcrAccessStatus) { this.tcrAccessStatus = tcrAccessStatus; }

    public Long getTcrcreatedby() { return tcrcreatedby; }
    public void setTcrcreatedby(Long tcrcreatedby) { this.tcrcreatedby = tcrcreatedby; }

    public LocalDateTime getTcrcreatedat() { return tcrcreatedat; }
    public void setTcrcreatedat(LocalDateTime tcrcreatedat) { this.tcrcreatedat = tcrcreatedat; }

    public Long getTcrlastupdatedby() { return tcrlastupdatedby; }
    public void setTcrlastupdatedby(Long tcrlastupdatedby) { this.tcrlastupdatedby = tcrlastupdatedby; }

    public LocalDateTime getTcrlastupdatedat() { return tcrlastupdatedat; }
    public void setTcrlastupdatedat(LocalDateTime tcrlastupdatedat) { this.tcrlastupdatedat = tcrlastupdatedat; }

    public Integer getTcrrowstate() { return tcrrowstate; }
    public void setTcrrowstate(Integer tcrrowstate) { this.tcrrowstate = tcrrowstate; }
}
