package com.vrv.vap.browser.controller;

import com.vrv.vap.browser.service.SysRoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

        // start ，从0,1,2 【下标从2开始，也就是第三条数据开始】
        String content = "{\"start\":\"2\",\"size\":\"3\",\"order\":\"id\",\"by\":\"desc\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/role/pagination").contentType("application/json;charset=UTF-8")
                .content(content))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void queryRoleByExampleAndPagination() throws Exception {
        Object result = mockMvc.perform(MockMvcRequestBuilders.get("/role").param("name", "")
                .param("page", "1").param("sort", "age asc").param("size", "2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

//        Todo json的传递方式
//        String content = "{\"username\":\"test123qwe\",\"name\":\"那么部门2222\",\"password\":\"password1121\"}";
//        String result = mockMvc.perform(get("/role").contentType("application/json;charset=UTF-8")
//                .content(content))
//                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.id").value("1"))
//                .andReturn().getResponse().getContentAsString();
//        System.out.println(result);
    }

}