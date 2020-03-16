package com.kanguan.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZSS
 * @date 2020/3/16 14:47
 * @description mybatis-plus 配置
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.kanguan.mapper")
public class MyBatisPlusConfig {
    
}
