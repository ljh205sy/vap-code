package com.vrv.vap.browser.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tclass")
public class Tclass {
    @Id
    @Column(name = "Guid")
    private String guid;

    @Column(name = "RegionManagerID")
    private String regionmanagerid;

    @Column(name = "ClassId")
    private String classid;

    @Column(name = "ClassName")
    private String classname;

    @Column(name = "UpID")
    private String upid;

    @Column(name = "OrganCode")
    private String organcode;

    private String orderfield;

    @Column(name = "securityId")
    private String securityid;

    @Column(name = "reg_type")
    private String regType;

    private String level;

    private List<Tclass> children = new ArrayList<>();

    public List<Tclass> getChildren() {
        return children;
    }

    public void setChildren(List<Tclass> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Tclass{" +
                "guid='" + guid + '\'' +
                ", upid='" + upid + '\'' +
                ", level='" + level + '\'' +
                ", regionmanagerid='" + regionmanagerid + '\'' +
                ", classid='" + classid + '\'' +
                ", classname='" + classname + '\'' +
                ", organcode='" + organcode + '\'' +
                ", orderfield='" + orderfield + '\'' +
                ", securityid='" + securityid + '\'' +
                ", regType='" + regType + '\'' +

                '}';
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return Guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return RegionManagerID
     */
    public String getRegionmanagerid() {
        return regionmanagerid;
    }

    /**
     * @param regionmanagerid
     */
    public void setRegionmanagerid(String regionmanagerid) {
        this.regionmanagerid = regionmanagerid;
    }

    /**
     * @return ClassId
     */
    public String getClassid() {
        return classid;
    }

    /**
     * @param classid
     */
    public void setClassid(String classid) {
        this.classid = classid;
    }

    /**
     * @return ClassName
     */
    public String getClassname() {
        return classname;
    }

    /**
     * @param classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * @return UpID
     */
    public String getUpid() {
        return upid;
    }

    /**
     * @param upid
     */
    public void setUpid(String upid) {
        this.upid = upid;
    }

    /**
     * @return OrganCode
     */
    public String getOrgancode() {
        return organcode;
    }

    /**
     * @param organcode
     */
    public void setOrgancode(String organcode) {
        this.organcode = organcode;
    }

    /**
     * @return orderfield
     */
    public String getOrderfield() {
        return orderfield;
    }

    /**
     * @param orderfield
     */
    public void setOrderfield(String orderfield) {
        this.orderfield = orderfield;
    }

    /**
     * @return securityId
     */
    public String getSecurityid() {
        return securityid;
    }

    /**
     * @param securityid
     */
    public void setSecurityid(String securityid) {
        this.securityid = securityid;
    }

    /**
     * @return reg_type
     */
    public String getRegType() {
        return regType;
    }

    /**
     * @param regType
     */
    public void setRegType(String regType) {
        this.regType = regType;
    }
}