package com.vrv.vap.browser.controller;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author liujinhui
 * date 2021/4/2 21:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysMenuControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void queryMenuList() throws Exception {
        String queryJson = "{\"name\": \"用户管理\"}";
        String content = mockMvc.perform(MockMvcRequestBuilders.get("/menu").content(queryJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    @Transactional
    @Rollback
    public void insertMenu() throws Exception {
        String insertJson = "{\n" +
                "  \"createTime\": \"2019-07-29T08:56:59.000+0000\",\n" +
                "  \"updateTime\": \"2019-07-29T08:56:59.000+0000\",\n" +
                "  \"parentId\": 2,\n" +
                "  \"name\": \"用户列表\",\n" +
                "  \"css\": null,\n" +
                "  \"url\": \"/api-user/users\",\n" +
                "  \"path\": \"user-list\",\n" +
                "  \"sort\": 1,\n" +
                "  \"type\": 2,\n" +
                "  \"hidden\": false,\n" +
                "  \"pathMethod\": \"GET\",\n" +
                "  \"subMenus\": null,\n" +
                "  \"roleId\": null,\n" +
                "  \"menuIds\": null\n" +
                "}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/menu").content(insertJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void updateMenu() throws Exception {
        String updateJson = "{\n" +
                "  \"id\": \"90\",\n" +
                "  \"createTime\": \"2019-07-29T08:56:59.000+0000\",\n" +
                "  \"updateTime\": \"2019-07-29T08:56:59.000+0000\",\n" +
                "  \"parentId\": 2,\n" +
                "  \"name\": \"用户列表\",\n" +
                "  \"css\": null,\n" +
                "  \"url\": \"/api-user/users\",\n" +
                "  \"path\": \"user-list\",\n" +
                "  \"sort\": 1,\n" +
                "  \"type\": 2,\n" +
                "  \"hidden\": false,\n" +
                "  \"pathMethod\": \"GET\",\n" +
                "  \"subMenus\": null,\n" +
                "  \"roleId\": null,\n" +
                "  \"menuIds\": null\n" +
                "}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/menu").content(updateJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void deleteMenu() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/menu/91").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}