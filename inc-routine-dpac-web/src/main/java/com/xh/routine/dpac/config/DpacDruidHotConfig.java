package com.xh.routine.dpac.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DpacDruidHotConfig {

    @Value("${spring.datasource.druid.hot.jdbc-url}")
    private String url;
    @Value("${spring.datasource.druid.hot.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.druid.hot.username}")
    private String username;
    @Value("${spring.datasource.druid.hot.password}")
    private String password;
    @Value("${spring.datasource.druid.hot.initialSize}")
    private Integer initialSize;
    @Value("${spring.datasource.druid.hot.max-active}")
    private Integer maxActive;

    @Bean(name = "hotDataSource")
    public DataSource hotDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setDriverClassName(driverClassName);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setInitialSize(initialSize);
        datasource.setMaxActive(maxActive);
        return datasource;
    }
}
