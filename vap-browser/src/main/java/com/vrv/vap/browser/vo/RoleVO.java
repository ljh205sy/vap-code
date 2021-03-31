package com.vrv.vap.browser.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liujinhui
 * date 2021/3/30 21:38
 */
@Data
public class RoleVO implements Serializable {

    private Integer id;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

}
