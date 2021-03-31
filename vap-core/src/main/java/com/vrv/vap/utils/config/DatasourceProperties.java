package com.vrv.vap.utils.config;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 14:38
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: liujinhui
 * @Date: 2019/11/9 13:39
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceProperties {
    private String url;
    private String username;
    private String password;
    private String driverClass;
    private int maxActive;
    private int minIdle;
    private int initialSize;
    private boolean testOnBorrow;
    private String loginUsername;
    private String loginPassword;

}
