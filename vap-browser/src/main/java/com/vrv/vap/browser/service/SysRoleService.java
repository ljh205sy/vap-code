package com.vrv.vap.browser.service;

import com.vrv.vap.browser.domain.SysRole;
import com.vrv.vap.core.service.BaseService;

import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 21:27
 */
public interface SysRoleService extends BaseService<SysRole,Integer> {

    List<SysRole> queryRoleByExampleAndPagination(Integer page, Integer rows, String rname);

    /**
     * 依赖角色ID，查询所有菜单
     * @param rid 角色ID
     * @return 返回角色列表
     */
    SysRole queryMenusByRid(Integer rid);
}
