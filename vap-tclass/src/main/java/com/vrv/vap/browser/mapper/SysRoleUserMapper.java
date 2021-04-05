package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.SysRoleUser;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysRoleUserMapper extends MySqlMapper<SysRoleUser>, Mapper<SysRoleUser>, IdsMapper<SysRoleUser> {
}