/*
 * Copyright (c) 2017. Shanghai Zhenhui Information Technology Co,. ltd.
 * All rights are reserved.
 */

package com.cbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Base64;

/**
 *
 * Helios Datasource Configuration
 *
 * Created by markfredchen on 18/04/2017.
 */
@Configuration
@EnableConfigurationProperties({ DataSourceProperties.class, MybatisPlusProperties.class})
public class DatabaseConfiguration {

    private final Environment env;

    private final DataSourceProperties dataSourceProperties;

    private final MybatisPlusProperties properties;

    private final ResourceLoader resourceLoader;


    public DatabaseConfiguration(Environment env, DataSourceProperties dataSourceProperties, MybatisPlusProperties properties, ResourceLoader resourceLoader) {
        this.env = env;
        this.dataSourceProperties = dataSourceProperties;
        this.properties = properties;
        this.resourceLoader = (resourceLoader == null) ?  new DefaultResourceLoader() : resourceLoader;
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(new String(Base64.getDecoder().decode(dataSourceProperties.getPassword())));
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }


    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource());
        mybatisSqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            mybatisSqlSessionFactoryBean.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        mybatisSqlSessionFactoryBean.setConfiguration(properties.getConfiguration());
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setIdType(2);
        globalConfig.setDbColumnUnderline(true);
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            mybatisSqlSessionFactoryBean.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            mybatisSqlSessionFactoryBean.setTypeHandlersPackage("com.helioscloud.atlantis.persistence.typehandler," + this.properties.getTypeHandlersPackage());
        } else {
            // By default, helios-core type handlers need to be included.
            mybatisSqlSessionFactoryBean.setTypeHandlersPackage("com.helioscloud.atlantis.persistence.typehandler");
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisSqlSessionFactoryBean.setMapperLocations(this.properties.resolveMapperLocations());
        }
        return mybatisSqlSessionFactoryBean;
    }

}
