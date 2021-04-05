package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.SysRole;
import com.vrv.vap.core.mapper.MybatisMapper;

import java.util.List;

/**
 * @author wh1107066
 */
public interface SysRoleMapper extends MybatisMapper<SysRole, Integer> {

    /**
     * 通过用户查询角色
     * @param uid 用户id
     * @return 返回该用户下的角色列表
     */
    List<SysRole> queryRolesByUid(Integer uid);


    /**
     * 通过角色查询资源
     * @param rid 角色ID
     * @return  返回该角色下的所有资源
     */
    SysRole queryMenusByRid(Integer rid);
}