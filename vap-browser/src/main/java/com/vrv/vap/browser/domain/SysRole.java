package com.vrv.vap.browser.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 租户字段
     */
    @Column(name = "tenant_id")
    private String tenantId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色code
     *
     * @return code - 角色code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色code
     *
     * @param code 角色code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取角色名
     *
     * @return name - 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取租户字段
     *
     * @return tenant_id - 租户字段
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户字段
     *
     * @param tenantId 租户字段
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}