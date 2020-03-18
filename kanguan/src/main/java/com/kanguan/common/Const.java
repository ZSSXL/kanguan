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

    public interface PATH{

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
