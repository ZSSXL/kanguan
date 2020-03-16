package com.kanguan;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZSS
 * @date 2020/3/16 14:55
 * @description 测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.kanguan.mapper")
public class BaseTest {

    @Before
    public void beforeTest(){
        System.out.println("=============================== 开始测试 ==============================");
    }

    @After
    public void afterTest(){
        System.out.println("=============================== 结束测试 ==============================");
    }

}
