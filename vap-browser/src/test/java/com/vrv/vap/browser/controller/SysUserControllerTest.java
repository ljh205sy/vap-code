package com.vrv.vap.browser.controller;

import com.vrv.vap.browser.domain.SysUser;
import com.vrv.vap.browser.service.SysUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author liujinhui
 * date 2021/3/26 11:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserControllerTest {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Transactional
    @Rollback(false)
    public void whenCreateUser() {
        SysUser user = new SysUser();
        user.setUsername("demo");
        user.setNickname("demo管理员");
        user.setPassword("123456");
        user.setType("APP");
        user.setEnabled(true);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void whenCreateUserSuccess() throws Exception {

        String content = "{\"username\":\"test123qwe\",\"name\":\"那么部门2222\",\"password\":\"password1121\"}";
        String result = mockMvc.perform(post("/user").contentType("application/json;charset=UTF-8")
                .content(content))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    /**
     * 新增用户，同时新增角色
     *
     * @throws Exception
     */
    @Test
    public void testInsertUser() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String birthday = formater.format(date);

        // 包含角色
        String content = "{\"roles\":[{\"id\":\"6\",\"name\":\"管理员\"},{\"id\":\"17\",\"name\":\"用户\"}],\"avatar\":\"avatar123\",\"username\":\"test123qwe\",\"sex\":\"1\",\"name\":\"那么部门2222\",\"password\":\"password1121\",\"birthday\":\"" + birthday + "\",\"status\":\"1\",\"phone\":\"18627868569\",\"email\":\"1634848393@qq.com\",\"create_time\":\"" + birthday + "\",\"update_time\":\"" + birthday + "\"}";

        // 无角色
        String content2 = "{\"avatar\":\"avatar111123\",\"username\":\"test123qwe\",\"sex\":\"1\",\"name\":\"那么部门2222\",\"password\":\"password1121\",\"status\":\"1\",\"phone\":\"18627868569\",\"email\":\"1634848393@qq.com\"}";

        System.out.println(content);
        String result = mockMvc.perform(post("/user").contentType("application/json;charset=UTF-8")
                .content(content2))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testUpdateUser() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String birthday = formater.format(date);
        // 无角色
        String content = "{\"id\":\"260\",\"avatar\":\"avatar11\",\"username\":\"test123qwe\",\"sex\":\"1\",\"name\":\"那么部门2222\",\"password\":\"password1121\",\"status\":\"1\",\"phone\":\"18627868569\",\"email\":\"1634848393@qq.com\"}";
        // 有角色
        String content2 = "{\"id\":\"260\",\"roles\":[{\"id\":\"6\",\"name\":\"管理员\"},{\"id\":\"17\",\"name\":\"用户\"}],\"avatar\":\"avatar123\",\"username\":\"test123qwe\",\"sex\":\"1\",\"name\":\"那么部门2222\",\"password\":\"password1121\",\"birthday\":\"" + birthday + "\",\"status\":\"1\",\"phone\":\"18627868569\",\"email\":\"1634848393@qq.com\",\"create_time\":\"" + birthday + "\",\"update_time\":\"" + birthday + "\"}";

        System.out.println(content2);
        String result = mockMvc.perform(put("/user").contentType("application/json;charset=UTF-8")
                .content(content2))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}