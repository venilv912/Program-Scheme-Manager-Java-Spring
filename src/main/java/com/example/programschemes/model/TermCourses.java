package com.example.programschemes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "termcourses", schema="ec2")
public class TermCourses {

    @Id
    @Column(name = "tcrid")
    private Long tcrid;

    @Column(name = "tcrtrmid")
    private Long tcrtrmid;

    @Column(name = "tcrcrsid")
    private Long tcrcrsid;

    @Column(name = "tcrtype")
    private String tcrtype;

    @Column(name = "tcrfacultyid")
    private Long tcrfacultyid;

    @Column(name = "tcrroundlogic")
    private String tcrroundlogic;

    @Column(name = "tcrmarks")
    private Short tcrmarks;

    @Column(name = "tcrstatus")
    private String tcrstatus;

    @Column(name = "tcr_access_status")
    private String tcrAccessStatus;

    @Column(name = "tcrcreatedby")
    private Long tcrcreatedby;

    @Column(name = "tcrcreatedat")
    private LocalDateTime tcrcreatedat;

    @Column(name = "tcrlastupdatedby")
    private Long tcrlastupdatedby;

    @Column(name = "tcrlastupdatedat")
    private LocalDateTime tcrlastupdatedat;

    @Column(name = "tcrrowstate")
    private Integer tcrrowstate;


    public Long getTcrid() { return tcrid; }
    public void setTcrid(Long tcrid) { this.tcrid = tcrid; }

    public Long getTcrtrmid() { return tcrtrmid; }
    public void setTcrtrmid(Long tcrtrmid) { this.tcrtrmid = tcrtrmid; }

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