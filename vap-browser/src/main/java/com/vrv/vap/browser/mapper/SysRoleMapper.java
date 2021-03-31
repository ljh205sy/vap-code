package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.SysRole;
import com.vrv.vap.utils.mapper.MybatisMapper;

import java.util.List;

public interface SysRoleMapper extends MybatisMapper<SysRole, Integer> {

    List<SysRole> queryRolesByUid(Integer uid);
}