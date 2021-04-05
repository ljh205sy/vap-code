package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.SysMenu;
import com.vrv.vap.core.mapper.MybatisMapper;

import java.util.List;

/**
 * @author wh1107066
 */
public interface SysMenuMapper extends MybatisMapper<SysMenu, Integer> {

    /**
     * 根据角色返回菜单
     * @param rid 角色ID
     * @return 返回SysMenu集合
     */
    List<SysMenu> queryMenusByRoleId(Integer rid);
}