package com.vrv.vap.browser.controller;

import com.github.pagehelper.PageInfo;
import com.vrv.vap.browser.domain.SysMenu;
import com.vrv.vap.browser.domain.SysRole;
import com.vrv.vap.browser.service.SysRoleService;
import com.vrv.vap.browser.vo.RoleQuery;
import com.vrv.vap.core.common.PageResult;
import com.vrv.vap.core.common.Result;
import com.vrv.vap.core.common.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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

    /**
     * 直接获取参数进行查询
     *
     * @param name 界面上查询条件
     * @param page 第几个位置开始查找
     * @param rows 每次查找多少条数据
     * @return 返回分页对象
     */
    @GetMapping("/test")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "角色名称"),
            @ApiImplicitParam(name = "page", value = "第几条开始"),
            @ApiImplicitParam(name = "size", value = "每页显示几条")})
    @ApiOperation(value = "")
    public PageResult<SysRole> queryRoleByExampleAndPagination(@RequestParam String name, @RequestParam int page, @RequestParam(name = "size") int rows) {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        PageInfo<SysRole> pageExample = sysRoleService.findPageExample(page, rows, example);
        return ResultUtil.success(pageExample);
    }

    /**
     * 通过对象来封装
     * 参数为对象， 必须继承QueryCondition的，
     * 返回分页对象
     */
    @GetMapping
    @ApiImplicitParams({@ApiImplicitParam(name = "uid", value = "角色id")})
    @ApiOperation(value = "查询角色分页显示", notes = "询角色分页显示")
    public PageResult<SysRole> queryRoleAndPagination(@RequestBody RoleQuery roleQueryCondition) {
        PageInfo<SysRole> pageInfo = null;
        // 从指定下标开始
        int page = roleQueryCondition.getStart();
        // 每页显示多少条
        int size = roleQueryCondition.getSize();

        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        String rname = roleQueryCondition.getName();
        if (!StringUtils.isEmpty(rname)) {
            criteria.andLike("rname", "%" + rname + "%");
        }
        if (!StringUtils.isEmpty(roleQueryCondition.getOrder()) || !StringUtils.isEmpty(roleQueryCondition.getBy())) {
            // example.setOrderByClause("`index` ASC,`AFTER_CHECK_TIME` ASC");
            String sortOrderByClause = roleQueryCondition.getOrder() + " " + roleQueryCondition.getBy();
            example.setOrderByClause(sortOrderByClause);
            pageInfo = sysRoleService.findPageExample(page, size, example);
        }
        return ResultUtil.success(pageInfo);
    }

    /**
     * 依赖role的id进行查询对象，该对象不会返回资源列表信息
     *
     * @param rid 角色ID
     * @return result
     */
    @GetMapping("/{rid:\\d+}")
    public Result<SysRole> queryRoleById(@PathVariable Integer rid) {
        SysRole sysRole = sysRoleService.findByPrimaryKey(rid);
        List<SysMenu> menus = sysRole.getMenus();
        System.out.println(menus);
        return ResultUtil.success(sysRole);
    }

    /**
     * 角色新增
     *
     * @param sysRole 对象
     * @return 返回result
     */
    @PostMapping
    public Result<SysRole> insertRole(@RequestBody SysRole sysRole) {
        sysRoleService.insertSelective(sysRole);
        return ResultUtil.success(sysRole);
    }


    @DeleteMapping("/{rid:\\d+}")
    public Result<Boolean> deleteRoleById(@PathVariable Integer rid) {
        sysRoleService.deleteByPrimaryKey(rid);
        return ResultUtil.success(true);
    }

    @GetMapping("/menu/{rid:\\d+}")
    @ApiImplicitParams({@ApiImplicitParam(name = "rid", value = "角色ID", required = true, dataType = "Integer")})
    @ApiOperation(value = "角色ID加载该角色所有资源", notes = "角色ID不能为空")
    public Result<SysRole> querySysRoleAndMenus(@PathVariable Integer rid) {
        SysRole sysRole = sysRoleService.queryMenusByRid(rid);
        List<SysMenu> menus = sysRole.getMenus();
        System.out.println(menus);
        return ResultUtil.success(sysRole);
    }
}
