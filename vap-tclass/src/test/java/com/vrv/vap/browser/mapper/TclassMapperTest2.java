package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.Tclass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/4/1 23:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TclassMapperTest2 {

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
        List<Tclass> allRecursion = tclassMapper.findAllRecursionById("cacb2addc352459387608f3383601835$org");
        for (Tclass tclass : allRecursion) {
            System.out.println(tclass);
        }
    }

}