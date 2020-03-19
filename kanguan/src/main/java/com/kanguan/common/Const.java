package com.kanguan.common;

/**
 * @author ZSS
 * @date 2020/3/17 22:33
 * @description 常量类
 */
public class Const {

    /**
     * redis 前缀
     */
    public static String REDIS_PREFIX = "kanguan_";

    /**
     * 当前登录的管理员
     */
    public static String CURRENT_ADMIN = "currentAdmin";

    /**
     * 封面测试
     */
    public static String DEFAULT_COVER_PATH = "http://60.205.179.156:8001/about_time.jpg";

    /**
     * 存储路径
     */
    public interface path {

        /**
         * 封面图片地址
         */
        String COVER_PATH = "cover";

        /**
         * 字幕地址
         */
        String SUBTITLE_PATH = "subtitle";
    }
}
