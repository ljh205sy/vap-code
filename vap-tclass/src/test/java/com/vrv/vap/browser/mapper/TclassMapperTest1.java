package com.vrv.vap.browser.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrv.vap.browser.domain.Tclass;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujinhui
 * date 2021/4/1 23:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TclassMapperTest1 {

    @Resource
    private TclassMapper tclassMapper;

    @Test
    public void queryAll() {
        List<Tclass> tclasses = tclassMapper.selectAll();
        int i = 1;
        for (Tclass tclass : tclasses) {
            System.out.println(tclass);
            i++;
            System.out.println("第" + i + "条数据");
        }
    }

    @Test
    public void query() {
        List<Tclass> allRecursion = tclassMapper.findAllRecursion();
        for (Tclass tclass : allRecursion) {
            System.out.println(tclass);
        }
    }

    @Test
    public void testQueryTclassList() throws Exception{
        // 原始的数据
        List<Tclass> rootTclass = tclassMapper.findAllRecursion();
        String top1 = "cacb2addc352459387608f3383601835$org";
        // 最后的结果
        List<Tclass> menuList = new ArrayList<Tclass>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootTclass.size(); i++) {
            // 一级菜单的guid="cacb2addc352459387608f3383601835$org"
            Tclass tclass = rootTclass.get(i);
            if (top1.equals(tclass.getGuid())) {
                menuList.add(tclass);
                break;
            }
        }
        // 为一级菜单设置子节点，getChild是递归调用的
        for (int i = 0; i < menuList.size(); i++) {
            Tclass menu = menuList.get(i);
            menu.setChildren(getChild(menu.getGuid(), rootTclass));
        }

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("menu", menuList);
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(jsonMap);
        System.out.println(value);
    }

    /**
     * 递归查找子菜单
     *
     * @param id         当前菜单id
     * @param rootTclass 要查找的列表
     * @return
     */
    private List<Tclass> getChild(String id, List<Tclass> rootTclass) {
        // 子菜单
        List<Tclass> childList = new ArrayList<>();
        for (Tclass menu : rootTclass) {
            // 遍历所有节点，将父菜单id与数据中的所有的父节点id比较。 如果upid=当前传过来的节点，那么这个就是子节点
            if (StringUtils.isNotBlank(menu.getUpid())) {
                if (menu.getUpid().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Tclass menu : childList) {// 没有url子菜单还有子菜单
            // 递归
            menu.setChildren(getChild(menu.getGuid(), rootTclass));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}