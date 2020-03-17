package com.kanguan.util;

import com.kanguan.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author ZSS
 * @date 2020/03/16 17:25
 * @description redis 连接池工具
 */
@Slf4j
@Component
public class RedisPoolUtil {

    /**
     * 设置redis, 并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @return String
     */
    public static String setAndExpire(String key, String value) {
        Jedis jedis = null;
        String result;
        try {
            int seconds = 60 * 3;
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error("set key and expire:{} value:{} error", key, value, e);
            assert jedis != null;
            RedisPool.returnResource(jedis);
            return null;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 获取值
     *
     * @param key 键
     * @return String
     */
    public static String get(String key) {
        Jedis jedis = null;
        String result;

        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("set key:{} error", key, e);
            assert jedis != null;
            RedisPool.returnResource(jedis);
            return null;
        }
        RedisPool.returnResource(jedis);
        return result;
    }


    /**
     * 删除
     *
     * @param key 键
     * @return String
     */
    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;

        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("set key:{} error", key, e);
            assert jedis != null;
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

}
