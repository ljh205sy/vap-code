package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.SysUser;
import com.vrv.vap.utils.mapper.MybatisMapper;

import java.util.List;

public interface SysUserMapper extends MybatisMapper<SysUser, Integer> {

    /**
     *  通过用户返回用户的相关信息，包括角色信息
     * @param uid 用户id
     * @return  返回用户对象
     */
    SysUser queryUserAndRolesByUid(Integer uid);

    List<SysUser> queryUserAndRoles();
}