package com.vrv.vap.browser.service.impl;

import com.vrv.vap.browser.domain.SysUser;
import com.vrv.vap.browser.mapper.SysUserMapper;
import com.vrv.vap.browser.service.SysUserService;
import com.vrv.vap.utils.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 20:49
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Integer> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser queryUserAndRolesByUid(Integer uid) {
        return sysUserMapper.queryUserAndRolesByUid(uid);
    }

    @Override
    public List<SysUser> queryUserAndRoles() {
        return sysUserMapper.queryUserAndRoles();
    }

}
