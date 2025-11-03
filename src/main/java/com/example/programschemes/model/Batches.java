package com.example.programschemes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "batches", schema="ec2")
public class Batches {

    @Id
    @Column(name = "bchid")
    private Short bchid;

    @Column(name = "bchprgid")
    private Short bchprgid;

    @Column(name = "bchcalid")
    private Short bchcalid;

    @Column(name = "bchname")
    private String bchname;

    @Column(name = "bchcapacity")
    private Short bchcapacity;

    @Column(name = "bchfield1")
    private Short bchfield1;

    @Column(name = "bchcreatedby")
    private Long bchcreatedby;

    @Column(name = "bchcreatedat")
    private LocalDateTime bchcreatedat;

    @Column(name = "bchlastupdatedby")
    private Long bchlastupdatedby;

    @Column(name = "bchlastupdatedat")
    private LocalDateTime bchlastupdatedat;

    @Column(name = "bchrowstate")
    private Short bchrowstate;

    @Column(name = "bchinstcode")
    private String bchinstcode;

    @Column(name = "scheme_id")
    private Integer schemeId;

    public Short getBchid() { return bchid; }
    public void setBchid(Short bchid) { this.bchid = bchid; }

    public Short getBchprgid() { return bchprgid; }
    public void setBchprgid(Short bchprgid) { this.bchprgid = bchprgid; }

    public Short getBchcalid() { return bchcalid; }
    public void setBchcalid(Short bchcalid) { this.bchcalid = bchcalid; }

    public String getBchname() { return bchname; }
    public void setBchname(String bchname) { this.bchname = bchname; }

    public Short getBchcapacity() { return bchcapacity; }
    public void setBchcapacity(Short bchcapacity) { this.bchcapacity = bchcapacity; }

    public Short getBchfield1() { return bchfield1; }
    public void setBchfield1(Short bchfield1) { this.bchfield1 = bchfield1; }

    public Long getBchcreatedby() { return bchcreatedby; }
    public void setBchcreatedby(Long bchcreatedby) { this.bchcreatedby = bchcreatedby; }

    public LocalDateTime getBchcreatedat() { return bchcreatedat; }
    public void setBchcreatedat(LocalDateTime bchcreatedat) { this.bchcreatedat = bchcreatedat; }

    public Long getBchlastupdatedby() { return bchlastupdatedby; }
    public void setBchlastupdatedby(Long bchlastupdatedby) { this.bchlastupdatedby = bchlastupdatedby; }

    public LocalDateTime getBchlastupdatedat() { return bchlastupdatedat; }
    public void setBchlastupdatedat(LocalDateTime bchlastupdatedat) { this.bchlastupdatedat = bchlastupdatedat; }

    public Short getBchrowstate() { return bchrowstate; }
    public void setBchrowstate(Short bchrowstate) { this.bchrowstate = bchrowstate; }

    public String getBchinstcode() { return bchinstcode; }
    public void setBchinstcode(String bchinstcode) { this.bchinstcode = bchinstcode; }

    public Integer getSchemeId() { return schemeId; }
    public void setSchemeId(Integer schemeId) { this.schemeId = schemeId; }
}