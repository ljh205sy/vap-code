package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.SysRoleMenu;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysRoleMenuMapper extends MySqlMapper<SysRoleMenu>, Mapper<SysRoleMenu>, IdsMapper<SysRoleMenu> {
}