package com.vrv.vap.browser.controller;

import com.github.pagehelper.PageInfo;
import com.vrv.vap.browser.domain.SysMenu;
import com.vrv.vap.browser.service.SysMenuService;
import com.vrv.vap.browser.vo.MenuQuery;
import com.vrv.vap.core.common.PageResult;
import com.vrv.vap.core.common.Result;
import com.vrv.vap.core.common.ResultUtil;
import com.vrv.vap.core.controller.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;

/**
 * @author liujinhui
 * date 2021/4/2 16:18
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理接口")
public class SysMenuController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 通过对象来封装
     * @ menuQuery.name 界面上查询条件
     * @ page 第几个位置开始查找
     * @ rows 每次查找多少条数据
     * 参数为对象， 必须继承Query对象
     * 返回分页对象
     */
    @GetMapping
    public PageResult<SysMenu> queryMenuAndPagination(@RequestBody MenuQuery menuQuery) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        String rname = menuQuery.getName();
        if (!StringUtils.isEmpty(rname)) {
            criteria.andLike("name", "%" + rname + "%");
        }
        orderBy(example, menuQuery);
        PageInfo<SysMenu> pageInfo = sysMenuService.findPageExample(menuQuery.getStart(), menuQuery.getSize(), example);
        if (logger.isDebugEnabled()) {
            printPageInfo(pageInfo);
        }
        return ResultUtil.success(pageInfo);
    }


    /**
     * 依赖role的id进行查询对象
     *
     * @param rid 角色ID
     * @return result
     */
    @GetMapping("/{rid:\\d+}")
    public Result<SysMenu> queryMenuById(@PathVariable Integer rid) {
        SysMenu sysMenu = sysMenuService.findByPrimaryKey(rid);
        return ResultUtil.success(sysMenu);
    }

    /**
     * 角色新增
     *
     * @param sysMenu 对象
     * @return 返回result
     */
    @PostMapping
    public Result<SysMenu> insertMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.insertSelective(sysMenu);
        return ResultUtil.success(sysMenu);
    }


    @DeleteMapping("/{mid:\\d+}")
    public Result<Boolean> deleteMenuById(@PathVariable Integer mid) {
        sysMenuService.deleteByPrimaryKey(mid);
        return ResultUtil.success(true);
    }

    @PutMapping
    public Result<Boolean> updateMenu(@Valid @RequestBody SysMenu sysMenu) {
        if (sysMenu == null || sysMenu.getId() == null) {
            throw new RuntimeException("删除菜单异常！");
        }
        sysMenuService.updateSelective(sysMenu);
        return ResultUtil.success(true);
    }
}
