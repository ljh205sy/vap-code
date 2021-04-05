package com.vrv.vap.browser.controller;

import com.vrv.vap.browser.service.SysRoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author liujinhui
 * date 2021/3/30 23:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysRoleControllerTest {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void queryRoleByPagination() throws Exception {
//        String content = "{\"name\":\"管理员\",\"start\":\"2\",\"size\":\"2\",\"order\":\"id\",\"by\":\"desc\"}";

        //        Todo json的传递方式
        // start ，从0,1,2 【下标从2开始，也就是第三条数据开始】
        String content = "{\"start\":\"2\",\"size\":\"3\",\"order\":\"id\",\"by\":\"desc\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/role").contentType("application/json;charset=UTF-8")
                .content(content))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void queryRoleByExampleAndPagination() throws Exception {
        Object result = mockMvc.perform(MockMvcRequestBuilders.get("/role/test").param("name", "")
                .param("page", "1").param("sort", "age asc").param("size", "2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

    @Test
    public void queryRoleById() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/role/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect((status().isOk()))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 不提交事务到数据库，进行回滚
     *
     * @throws Exception 捕获异常
     */
    @Test
    @Transactional
    @Rollback
    public void insertRole() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dd = sdf.format(date);
//        String role = "{\"code\":\"liu001\",\"name\":\"liu001\",\"tenantId\":\"webApp\"}";
        String role = "{\"code\":\"liu001\",\"name\":\"liu001\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"tenantId\":\"webApp\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/role").contentType(MediaType.APPLICATION_JSON_UTF8).content(role)).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 删除角色，事务回滚，不提交到数据库中
     *
     * @throws Exception 抛出异常
     */
    @Test
    @Transactional
    @Rollback
    public void deleteRole() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/role/20"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void querySysRoleAndMenus() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/role/menu/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}