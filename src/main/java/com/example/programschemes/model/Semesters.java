package com.example.programschemes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "semesters", schema="ec2")
public class Semesters {

    @Id
    @Column(name = "strid")
    private Short strid;

    @Column(name = "strbchid")
    private Short strbchid;

    @Column(name = "strtrmid")
    private Short strtrmid;

    @Column(name = "strcalid")
    private Short strcalid;

    @Column(name = "strname")
    private String strname;

    @Column(name = "strfield1")
    private String strfield1;

    @Column(name = "strfield2")
    private String strfield2;

    @Column(name = "strfield3")
    private String strfield3;

    @Column(name = "strcreatedby")
    private Long strcreatedby;

    @Column(name = "strcreatedat")
    private LocalDateTime strcreatedat;

    @Column(name = "strlastupdatedby")
    private Long strlastupdatedby;

    @Column(name = "strlastupdatedat")
    private LocalDateTime strlastupdatedat;

    @Column(name = "strrowstate")
    private Short strrowstate;

    @Column(name = "strseqno")
    private Short strseqno;

    @Column(name = "strstcid")
    private Short strstcid;

    @Column(name = "strresultdecdate")
    private LocalDateTime strresultdecdate;

    @Column(name = "strregstatus")
    private String strregstatus;

    @Column(name = "stradddropstatus")
    private String stradddropstatus;


    public Short getStrid() { return strid; }
    public void setStrid(Short strid) { this.strid = strid; }

    public Short getStrbchid() { return strbchid; }
    public void setStrbchid(Short strbchid) { this.strbchid = strbchid; }

    public Short getStrtrmid() { return strtrmid; }
    public void setStrtrmid(Short strtrmid) { this.strtrmid = strtrmid; }

    public Short getStrcalid() { return strcalid; }
    public void setStrcalid(Short strcalid) { this.strcalid = strcalid; }

    public String getStrname() { return strname; }
    public void setStrname(String strname) { this.strname = strname; }

    public String getStrfield1() { return strfield1; }
    public void setStrfield1(String strfield1) { this.strfield1 = strfield1; }

    public String getStrfield2() { return strfield2; }
    public void setStrfield2(String strfield2) { this.strfield2 = strfield2; }

    public String getStrfield3() { return strfield3; }
    public void setStrfield3(String strfield3) { this.strfield3 = strfield3; }

    public Long getStrcreatedby() { return strcreatedby; }
    public void setStrcreatedby(Long strcreatedby) { this.strcreatedby = strcreatedby; }

    public LocalDateTime getStrcreatedat() { return strcreatedat; }
    public void setStrcreatedat(LocalDateTime strcreatedat) { this.strcreatedat = strcreatedat; }

    public Long getStrlastupdatedby() { return strlastupdatedby; }
    public void setStrlastupdatedby(Long strlastupdatedby) { this.strlastupdatedby = strlastupdatedby; }

    public LocalDateTime getStrlastupdatedat() { return strlastupdatedat; }
    public void setStrlastupdatedat(LocalDateTime strlastupdatedat) { this.strlastupdatedat = strlastupdatedat; }

    public Short getStrrowstate() { return strrowstate; }
    public void setStrrowstate(Short strrowstate) { this.strrowstate = strrowstate; }

    public Short getStrseqno() { return strseqno; }
    public void setStrseqno(Short strseqno) { this.strseqno = strseqno; }

    public Short getStrstcid() { return strstcid; }
    public void setStrstcid(Short strstcid) { this.strstcid = strstcid; }

    public LocalDateTime getStrresultdecdate() { return strresultdecdate; }
    public void setStrresultdecdate(LocalDateTime strresultdecdate) { this.strresultdecdate = strresultdecdate; }

    public String getStrregstatus() { return strregstatus; }
    public void setStrregstatus(String strregstatus) { this.strregstatus = strregstatus; }

    public String getStradddropstatus() { return stradddropstatus; }
    public void setStradddropstatus(String stradddropstatus) { this.stradddropstatus = stradddropstatus; }

}