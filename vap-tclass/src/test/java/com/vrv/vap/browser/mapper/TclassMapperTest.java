package com.vrv.vap.browser.mapper;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrv.vap.browser.domain.Tclass;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
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
public class TclassMapperTest {

    @Resource
    private TclassMapper tclassMapper;

    @Test
    public void testQueryTclassList() throws Exception {
        // 原始的数据
        List<Tclass> rootTclass = tclassMapper.findAllTclass();
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

        // 获取所有数据，并进行级别设置
        List<Tclass> list = allData(menuList);

        // 拼接sql并写入到文件中
        printSql(list);


        // 树结构json解析
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(jsonMap);
        System.out.println(value);
    }

    private List<String> printSql(List<Tclass> insertSql) throws IOException {
        String dir = "D:\\2\\sql.sql";
        String table = "\n" +
                "DROP TABLE `tclass_temp` ;\n" +
                "CREATE TABLE `tclass_temp` (\n" +
                "  `Guid` VARCHAR(100) NOT NULL,\n" +
                "  `RegionManagerID` VARCHAR(32) DEFAULT NULL,\n" +
                "  `ClassId` VARCHAR(255) DEFAULT NULL,\n" +
                "  `ClassName` VARCHAR(255) DEFAULT NULL,\n" +
                "  `UpID` VARCHAR(255) DEFAULT NULL,\n" +
                "  `OrganCode` VARCHAR(255) DEFAULT NULL,\n" +
                "  `orderfield` VARCHAR(10) DEFAULT '',\n" +
                "  `securityId` VARCHAR(255) DEFAULT NULL,\n" +
                "  `reg_type` VARCHAR(50) DEFAULT 'tds',\n" +
                "  `level` VARCHAR(2000) DEFAULT '',\n" +
                "  `level` VARCHAR(2000) DEFAULT '',\n" +
                "   PRIMARY KEY (`Guid`)\n" +
                ") ENGINE=INNODB DEFAULT CHARSET=utf8;";
        System.out.println(table);
        String select = "SELECT guid, upid, classid, classname, `level`, orderfield FROM tclass_temp ORDER BY `level` ;";
        List<String> list = new ArrayList<>();

        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        else
            file.delete();
        //创建BufferedWriter对象并向文件写入内容
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        //向文件中写入内容
        try {
            bw.write(table);
            bw.newLine();
            bw.write(select);
            bw.newLine();
            for (Tclass tclass : insertSql) {
                int levelLength = tclass.getLevel().length() / 4;
                String space = "";
                if (levelLength == 1) {
                    space = "";
                } else {
                    // 第一级没空格，  第二级 2个空格     第三极  4个空格
                    for (int i = 0; i < (2 * levelLength - 2); i++) {
                        space = space.concat(" ");
                    }
                }

                String sql = "INSERT INTO tclass_temp (guid, RegionManagerID, ClassId, ClassName,upid,OrganCode, orderfield, securityId, reg_type, `level`) " +
                        "VALUES('" + tclass.getGuid() + "','" + tclass.getRegionmanagerid() + "','" + tclass.getClassid() + "','"
                        + space.concat(tclass.getClassname()) +
                        "','" + tclass.getUpid() + "','" + tclass.getOrgancode() + "','" + tclass.getOrderfield() + "','" + tclass.getSecurityid() + "','" + tclass.getRegType() + "','" + tclass.getLevel() + "'); ";
                bw.write(sql);
                bw.newLine();
                list.add(sql);
                if (insertSql.size() % 100 == 0) {  // 批量提交
                    System.out.println("100条提交一次");
                    bw.flush();
                }
            }
            // 最后提交一次
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
        return list;
    }

    //第二种方式:使用BuffredReader和BufferedWriter
    public void WriteSecondWay() throws IOException {
        String dir = "E:\\soft\\aaa\\b.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建BufferedWriter对象并向文件写入内容
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        //向文件中写入内容
        bw.write("the second way to write and read");
        bw.flush();
        bw.close();
        //创建BufferedReader读取文件内容
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private List<Tclass> allData(List<Tclass> menuList) {
        List<Tclass> allList = new ArrayList<>();
        for (int j = 0; j < menuList.size(); j++) {
            Tclass tclass = menuList.get(j);
            tclass.setLevel(formatLevel(j + 1));  // 一级level
            List<Tclass> children = tclass.getChildren();
            allList.add(tclass);  // 第一级加进去
            if (CollectionUtil.isNotEmpty(children)) {
                this.getChildrenList(allList, children, tclass.getLevel());
            }
        }
        return allList;
    }


    private void getChildrenList(List<Tclass> allList, List<Tclass> childrenList, String plevel) {
        if (childrenList != null && childrenList.size() > 0) {
            for (int j = 0; j < childrenList.size(); j++) {
                Tclass tclass = childrenList.get(j);
                tclass.setLevel(plevel + formatLevel(j + 1));
                allList.add(tclass);
                if (CollectionUtil.isNotEmpty(tclass.getChildren())) {
                    getChildrenList(allList, tclass.getChildren(), tclass.getLevel());
                }
            }
        }
    }

    private String formatLevel(int xx) {
        if (xx < 10) {
            return "000" + String.valueOf(xx);
        }
        if (xx < 100) {
            return "00" + String.valueOf(xx);
        }
        if (xx < 1000) {
            return "0" + String.valueOf(xx);
        }
        return "";
    }

    /**
     * 递归查找子菜单
     *
     * @param id         当前菜单id
     * @param rootTclass 要查找的列表
     * @return 返回树形结构
     */
    private List<Tclass> getChild(String id, List<Tclass> rootTclass) {
        // 子菜单
        List<Tclass> childList = new ArrayList<>();
        for (int j = 0; j < rootTclass.size(); j++) {
            Tclass tclass = rootTclass.get(j);
            // 遍历所有节点，将父菜单id与数据中的所有的父节点id比较。 如果upid=当前传过来的节点，那么这个就是子节点
            if (StringUtils.isNotBlank(tclass.getUpid())) {
                if (tclass.getUpid().equals(id)) {
                    childList.add(tclass);
                }
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (int z = 0; z < childList.size(); z++) {
            Tclass tclass = childList.get(z);
            // 递归
            tclass.setChildren(getChild(tclass.getGuid(), rootTclass));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}