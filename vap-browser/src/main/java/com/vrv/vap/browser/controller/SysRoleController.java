package com.vrv.vap.browser.controller;

import com.github.pagehelper.PageInfo;
import com.vrv.vap.browser.domain.SysRole;
import com.vrv.vap.browser.service.SysRoleService;
import com.vrv.vap.utils.common.Result;
import com.vrv.vap.utils.common.ResultUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

/**
 * @author liujinhui
 * date 2021/3/30 21:30
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理接口")
public class SysRoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping
    public Result<PageInfo<SysRole>> queryRoleByExampleAndPagination(@RequestParam String name, @RequestParam int page, @RequestParam(name="size") int rows){
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        PageInfo<SysRole> pageExample = sysRoleService.findPageExample(page, rows, example);
        return ResultUtil.success(pageExample);
    }
}
