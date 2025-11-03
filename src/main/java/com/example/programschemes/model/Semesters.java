package com.example.programschemes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "semesters", schema = "ec2")
public class Semesters {

    @Id
    @Column(name = "strid")
    private Short strid;

    @Column(name = "strbchid")
    private Short strbchid = 0;

    @Column(name = "strtrmid")
    private Short strtrmid = 0;

    @Column(name = "strname")
    private String strname = "U";

    @Column(name = "strfield1")
    private String strfield1 = "N";

    @Column(name = "strfield2")
    private String strfield2 = "N";

    @Column(name = "strfield3")
    private String strfield3 = "N";

    @Column(name = "strcreatedby")
    private Long strcreatedby = 0L;

    @Column(name = "strcreatedat")
    private LocalDateTime strcreatedat = LocalDateTime.now();

    @Column(name = "strlastupdatedby")
    private Long strlastupdatedby = 0L;

    @Column(name = "strlastupdatedat")
    private LocalDateTime strlastupdatedat = LocalDateTime.now();

    @Column(name = "strrowstate")
    private Short strrowstate = 1; // active

    @Column(name = "strseqno")
    private Short strseqno = 0;

    @Column(name = "strstcid")
    private Short strstcid = 0;

    @Column(name = "strresultdecdate")
    private LocalDateTime strresultdecdate = LocalDateTime.now();

    // ⚠ These two are likely CHAR(1) in DB, so use one-letter codes
    @Column(name = "strregstatus", length = 1)
    private String strregstatus = "P"; // Pending

    @Column(name = "stradddropstatus", length = 1)
    private String stradddropstatus = "C"; // Closed

    @PrePersist
    public void prePersist() {
        if (this.strname == null) this.strname = "U";
        if (this.strfield1 == null) this.strfield1 = "N";
        if (this.strfield2 == null) this.strfield2 = "N";
        if (this.strfield3 == null) this.strfield3 = "N";
        if (this.strcreatedat == null) this.strcreatedat = LocalDateTime.now();
        if (this.strlastupdatedat == null) this.strlastupdatedat = LocalDateTime.now();
        if (this.strresultdecdate == null) this.strresultdecdate = LocalDateTime.now();
        if (this.strcreatedby == null) this.strcreatedby = 0L;
        if (this.strlastupdatedby == null) this.strlastupdatedby = 0L;
        if (this.strbchid == null) this.strbchid = 0;
        if (this.strtrmid == null) this.strtrmid = 0;
        if (this.strrowstate == null) this.strrowstate = 1;
        if (this.strseqno == null) this.strseqno = 0;
        if (this.strstcid == null) this.strstcid = 0;
        if (this.strregstatus == null || this.strregstatus.length() != 1) this.strregstatus = "P";
        if (this.stradddropstatus == null || this.stradddropstatus.length() != 1) this.stradddropstatus = "C";
    }

    // Getters and Setters
    public Short getStrid() { return strid; }
    public void setStrid(Short strid) { this.strid = strid; }

    public Short getStrbchid() { return strbchid; }
    public void setStrbchid(Short strbchid) { this.strbchid = strbchid; }

    public Short getStrtrmid() { return strtrmid; }
    public void setStrtrmid(Short strtrmid) { this.strtrmid = strtrmid; }

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
