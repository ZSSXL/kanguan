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
     * 默认当前页
     */
    public static final String DEFAULT_PAGE_NUMBER = "1";

    /**
     * 默认分页条数
     */
    public static final String DEFAULT_PAGE_SIZE = "24";

    /**
     * 默认推荐适量
     */
    public static final Integer DEFAULT_RECOMMENDED_QUANTITY = 12;

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

    /**
     * 电影/电视剧
     */
    public interface type {
        /**
         * 电影
         */
        String MOVIE = "1";

        /**
         * 电视剧
         */
        String TV = "0";
    }

    /**
     * 反馈阅读状态
     */
    public interface read {
        /**
         * 已读
         */
        String YES = "1";
        /**
         * 未读
         */
        String NO = "0";
    }


    /**
     * 反馈请求状态
     */
    public interface exist {
        /**
         * 已反馈
         */
        String YES = "1";
        /**
         * 未反馈
         */
        String NO = "0";
    }

    /**
     * 会员状态
     */
    public interface member {
        /**
         * 是会员
         */
        String YES = "1";
        /**
         * 不是会员
         */
        String NO = "0";
    }
}
