package com.vrv.vap.browser.service;

import com.vrv.vap.browser.domain.SysUser;
import com.vrv.vap.utils.service.BaseService;

import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 20:49
 */
public interface SysUserService extends BaseService<SysUser,Integer> {
    /**
     * 获取所有的用户，并携带所有的角色信息
     *
     * @return
     */
    SysUser queryUserAndRolesByUid(Integer uid);

    List<SysUser> queryUserAndRoles();
}
