package com.kanguan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZSS
 */
@SpringBootApplication
@MapperScan("com.kanguan.mapper")
public class KanguanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanguanApplication.class, args);
    }

}
