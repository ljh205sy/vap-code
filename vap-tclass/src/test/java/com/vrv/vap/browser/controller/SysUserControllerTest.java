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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * @author liujinhui
 * date 2021/3/26 11:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 不包含角色，只增加用户信息，事务回滚
     *
     * @throws Exception 抛出异常
     */
    @Test
    @Rollback
    @Transactional
    public void insertUser() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dd = sdf.format(date);
        String context = "{\"username\":\"liu001\",\"password\":\"liu001\",\"nickname\":\"liu001\",\"mobile\":\"18627868759\",\"sex\":\"true\",\"enable\":\"true\",\"type\":\"APP\",\"company\":\"wuhan\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"isDelete\":\"1\"}";
        String result = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(context))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 提交事务到数据库中
     *
     * @throws Exception 异常
     */
    @Test
    @Transactional
    @Rollback(false)
    public void whenCreateUserSuccess() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dd = sdf.format(new Date());
        String content = "{\"username\":\"liu001\",\"password\":\"liu001\",\"nickname\":\"liu001\",\"mobile\":\"18627868759\",\"sex\":\"true\",\"enable\":\"true\",\"type\":\"APP\",\"company\":\"wuhan\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"isDelete\":\"1\"}";
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
    public void insertUserAndRoles() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dd = sdf.format(date);

        // 包含角色, 中间表只需要role的主键即可，其实json中只包含id即可，如果role2的json数据
        String role1 = "{\"id\":\"1\",\"code\":\"liu001\",\"name\":\"liu001\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"tenantId\":\"webApp\"}";
        String role2 = "{\"id\":\"2\",\"code\":\"liu001\"}";


        String content = "{\"roles\":[" + role1 + "," + role2 + "],\"username\":\"liu001\",\"password\":\"liu001\",\"nickname\":\"liu001\",\"mobile\":\"18627868759\",\"sex\":\"true\",\"enable\":\"true\",\"type\":\"APP\",\"company\":\"wuhan\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"isDelete\":\"1\"}";

        // 无角色,只是新增用户信息
//        String context = "{\"username\":\"liu001\",\"password\":\"liu001\",\"nickname\":\"liu001\",\"mobile\":\"18627868759\",\"sex\":\"true\",\"enable\":\"true\",\"type\":\"APP\",\"company\":\"wuhan\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"isDelete\":\"1\"}";
        String result = mockMvc.perform(post("/user").contentType("application/json;charset=UTF-8")
                .content(content))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 新增用户，不带角色
     * @throws Exception 抛出异常
     */
    @Test
    public void updateUserNoRoles() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dd = sdf.format(date);
        // 包含角色, 中间表只需要role的主键即可，其实json中只包含id即可，如果role2的json数据
        String role1 = "{\"id\":\"1\",\"code\":\"liu001\",\"name\":\"liu001\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"tenantId\":\"webApp\"}";
        String role2 = "{\"id\":\"2\",\"code\":\"liu001\"}";

        // 无角色
//        String content = "{\"id\":\"20\",\"username\":\"liu001\",\"password\":\"liu001\",\"nickname\":\"liu001\",\"mobile\":\"18627868759\",\"sex\":\"true\",\"enable\":\"true\",\"type\":\"APP\",\"company\":\"wuhan\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"isDelete\":\"1\"}";

        // 有角色
        String content = "{\"id\":\"20\",\"roles\":[" + role1 + "," + role2 + "],\"username\":\"liu002\",\"password\":\"liu002\",\"nickname\":\"liu001\",\"mobile\":\"18627868759\",\"sex\":\"true\",\"enable\":\"true\",\"type\":\"APP\",\"company\":\"wuhan\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"isDelete\":\"1\"}";
        String result = mockMvc.perform(put("/user").contentType("application/json;charset=UTF-8")
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 新增用户，同时新增角色
     * @throws Exception 抛出异常
     */
    @Test
    public void updateUserAndRoles() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dd = sdf.format(date);
        // 包含角色, 中间表只需要role的主键即可，其实json中只包含id即可，如果role2的json数据
        String role1 = "{\"id\":\"1\",\"code\":\"liu001\",\"name\":\"liu001\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"tenantId\":\"webApp\"}";
        String role2 = "{\"id\":\"2\",\"code\":\"liu001\"}";
        // 有角色
        String content = "{\"id\":\"20\",\"roles\":[" + role1 + "," + role2 + "],\"username\":\"liu002\",\"password\":\"liu002\",\"nickname\":\"liu001\",\"mobile\":\"18627868759\",\"sex\":\"true\",\"enable\":\"true\",\"type\":\"APP\",\"company\":\"wuhan\",\"createTime\":\"" + dd + "\",\"updateTime\":\"" + dd + "\",\"isDelete\":\"1\"}";
        String result = mockMvc.perform(put("/user").contentType("application/json;charset=UTF-8")
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 测试按照id删除用户
     * @throws Exception 抛出异常
     */
    @Test
    public void deleteUserById() throws Exception {
        String result = mockMvc.perform(delete("/user/20")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}