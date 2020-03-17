package com.kanguan.redis;

import com.kanguan.BaseTest;
import com.kanguan.util.RedisPoolUtil;
import org.junit.Test;

/**
 * @author ZSS
 * @date 2020/3/17 21:53
 * @description redis 测试
 */
public class RedisTest extends BaseTest {

    @Test
    public void redisTest(){
        String name = RedisPoolUtil.get("name");
        System.out.println(name);
    }

    @Test
    public void expireTest(){
        String age = RedisPoolUtil.setAndExpire("age", "24");
        System.out.println(age);
    }
}
