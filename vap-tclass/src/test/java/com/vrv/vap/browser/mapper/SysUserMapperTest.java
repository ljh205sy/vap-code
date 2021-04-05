package com.vrv.vap.browser.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vrv.vap.browser.domain.SysRole;
import com.vrv.vap.browser.domain.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author liujinhui
 * date 2021/3/30 17:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserMapperTest {

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    public void querySelectPageInfo() {
        PageHelper.startPage(1, 3);
        List<SysUser> userList = sysUserMapper.selectAll();
        for (SysUser sysUser : userList) {
            System.out.println(sysUser);
        }
        Assert.assertNotNull(userList);
        System.out.println("查询出数量：" + userList.size());
        //pageInfo:将分页数据和显示的数据封装到PageInfo当中
        PageInfo<SysUser> pageInfo = PageInfo.of(userList);
        //将封装好的数据返回到前台页面， 返回ResponseBody
        System.out.println("总数量：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("每页显示条数：" + pageInfo.getPageSize());
        System.out.println("第几页：" + pageInfo.getPageNum());
        System.out.println("当前页面的总数量：" + pageInfo.getSize());
    }


    @Test
    public void querySelectByExample() {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        //模糊查询
        if (!StringUtils.isEmpty(sysUser.getUsername())) {
            criteria.andLike("username", "%" + sysUser.getUsername() + "%");
        }
        List<SysUser> userList = sysUserMapper.selectByExample(example);
        for (SysUser user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void insertSelective(){
        SysUser user = new SysUser();
        user.setUsername("demo");
        user.setNickname("demo管理员");
        user.setPassword("123456");
        user.setType("APP");
        user.setEnabled(true);
        int rows = sysUserMapper.insertSelective(user);
    }

    @Test
    public void queryUserAndRolesByUid(){
        SysUser user = sysUserMapper.queryUserAndRolesByUid(2);
        if (Optional.of(user).isPresent()) {
            List<SysRole> rlist = user.getRoles();
            for (SysRole sysRole : rlist) {
                System.out.println(sysRole);
            }
        }
        System.out.println(user);
    }
}