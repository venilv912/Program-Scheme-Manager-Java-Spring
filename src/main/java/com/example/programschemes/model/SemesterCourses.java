package com.example.programschemes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "semestercourses", schema = "ec2")
public class SemesterCourses {

    @Id
    @Column(name = "scrid")
    private Long scrid;

    @Column(name = "scrstrid")
    private Short scrstrid = 0;

    @Column(name = "screlective", length = 1)
    private String screlective = "N"; // N = Non-elective, Y = Elective

    @Column(name = "scrcrsid")
    private Long scrcrsid = 0L;

    @Column(name = "scrcgpid")
    private Long srcgpid = 0L;

    @Column(name = "scrcreatedby")
    private Long scrcreatedby = 0L;

    @Column(name = "scrcreatedat")
    private LocalDateTime scrcreatedat = LocalDateTime.now();

    @Column(name = "scrlastupdatedby")
    private Long scrlastupdatedby = 0L;

    @Column(name = "scrlastupdatedat")
    private LocalDateTime scrlastupdatedat = LocalDateTime.now();

    @Column(name = "scrrowstate")
    private Integer scrrowstate = 1;

    @Column(name = "scrtcrid")
    private Long scrtcrid = 0L;

    // Auto-default setter before inserting
    @PrePersist
    public void prePersist() {
        if (this.scrstrid == null) this.scrstrid = 0;
        if (this.screlective == null || this.screlective.length() != 1) this.screlective = "N";
        if (this.scrcrsid == null) this.scrcrsid = 0L;
        if (this.srcgpid == null) this.srcgpid = 0L;
        if (this.scrcreatedby == null) this.scrcreatedby = 0L;
        if (this.scrlastupdatedby == null) this.scrlastupdatedby = 0L;
        if (this.scrcreatedat == null) this.scrcreatedat = LocalDateTime.now();
        if (this.scrlastupdatedat == null) this.scrlastupdatedat = LocalDateTime.now();
        if (this.scrrowstate == null) this.scrrowstate = 1;
        if (this.scrtcrid == null) this.scrtcrid = 0L;
    }

    // Getters & Setters
    public Long getScrid() { return scrid; }
    public void setScrid(Long scrid) { this.scrid = scrid; }

    public Short getScrstrid() { return scrstrid; }
    public void setScrstrid(Short scrstrid) { this.scrstrid = scrstrid; }

    public String getScrelective() { return screlective; }
    public void setScrelective(String screlective) { this.screlective = screlective; }

    public Long getScrcrsid() { return scrcrsid; }
    public void setScrcrsid(Long scrcrsid) { this.scrcrsid = scrcrsid; }

    public Long getSrcgpid() { return srcgpid; }
    public void setSrcgpid(Long srcgpid) { this.srcgpid = srcgpid; }

    public Long getScrcreatedby() { return scrcreatedby; }
    public void setScrcreatedby(Long scrcreatedby) { this.scrcreatedby = scrcreatedby; }

    public LocalDateTime getScrcreatedat() { return scrcreatedat; }
    public void setScrcreatedat(LocalDateTime scrcreatedat) { this.scrcreatedat = scrcreatedat; }

    public Long getScrlastupdatedby() { return scrlastupdatedby; }
    public void setScrlastupdatedby(Long scrlastupdatedby) { this.scrlastupdatedby = scrlastupdatedby; }

    public LocalDateTime getScrlastupdatedat() { return scrlastupdatedat; }
    public void setScrlastupdatedat(LocalDateTime scrlastupdatedat) { this.scrlastupdatedat = scrlastupdatedat; }

    public Integer getScrrowstate() { return scrrowstate; }
    public void setScrrowstate(Integer scrrowstate) { this.scrrowstate = scrrowstate; }

    public Long getScrtcrid() { return scrtcrid; }
    public void setScrtcrid(Long scrtcrid) { this.scrtcrid = scrtcrid; }
}
