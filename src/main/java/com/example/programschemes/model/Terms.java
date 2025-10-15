package com.example.programschemes.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "terms", schema="ec2")
public class Terms {

    @Id
    @Column(name = "trmid")
    private Short trmid;

    @Column(name = "trmayrid")
    private Short trmayrid;

    @Column(name = "trmname")
    private String trmname;

    @Column(name = "trmseqno")
    private Short trmseqno;

    @Column(name = "trm_starts")
    private LocalDate trmStarts;

    @Column(name = "trm_ends")
    private LocalDate trmEnds;

    @Column(name = "trmcreatedby")
    private Long trmcreatedby;

    @Column(name = "trmcreatedat")
    private LocalDateTime trmcreatedat;

    @Column(name = "trmlastupdatedby")
    private Long trmlastupdatedby;

    @Column(name = "trmlastupdatedat")
    private LocalDateTime trmlastupdatedat;

    @Column(name = "trmrowstate")
    private Short trmrowstate;


    public Short getTrmid() { return trmid; }
    public void setTrmid(Short trmid) { this.trmid = trmid; }

    public Short getTrmayrid() { return trmayrid; }
    public void setTrmayrid(Short trmayrid) { this.trmayrid = trmayrid; }

    public String getTrmname() { return trmname; }
    public void setTrmname(String trmname) { this.trmname = trmname; }

    public Short getTrmseqno() { return trmseqno; }
    public void setTrmseqno(Short trmseqno) { this.trmseqno = trmseqno; }

    public LocalDate getTrmstarts() { return trmStarts; }
    public void setTrmstarts(LocalDate trmStarts) { this.trmStarts = trmStarts; }

    public LocalDate getTrmends() { return trmEnds; }
    public void setTrmends(LocalDate trmEnds) { this.trmEnds = trmEnds; }

    public Long getTrmcreatedby() { return trmcreatedby; }
    public void setTrmcreatedby(Long trmcreatedby) { this.trmcreatedby = trmcreatedby; }

    public LocalDateTime getTrmcreatedat() { return trmcreatedat; }
    public void setTrmcreatedat(LocalDateTime trmcreatedat) { this.trmcreatedat = trmcreatedat; }

    public Long getTrmlastupdatedby() { return trmlastupdatedby; }
    public void setTrmlastupdatedby(Long trmlastupdatedby) { this.trmlastupdatedby = trmlastupdatedby; }

    public LocalDateTime getTrmlastupdatedat() { return trmlastupdatedat; }
    public void setTrmlastupdatedat(LocalDateTime trmlastupdatedat) { this.trmlastupdatedat = trmlastupdatedat; }

    public Short getTrmrowstate() { return trmrowstate; }
    public void setTrmrowstate(Short trmrowstate) { this.trmrowstate = trmrowstate; }

}