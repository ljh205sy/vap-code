package com.vrv.vap.browser.service;

import com.vrv.vap.browser.domain.SysRole;
import com.vrv.vap.utils.service.BaseService;

import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 21:27
 */
public interface SysRoleService extends BaseService<SysRole,Integer> {

    List<SysRole> queryRoleByExampleAndPagination(Integer page, Integer rows, String rname);
}
