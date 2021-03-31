package com.vrv.vap.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author liujinhui
 * date 2021/3/30 16:53
 */
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.vrv.vap.browser.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class BrowserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrowserApplication.class, args);
    }
}
