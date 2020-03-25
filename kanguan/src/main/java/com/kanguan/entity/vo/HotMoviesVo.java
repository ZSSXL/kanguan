package com.kanguan.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/25 14:21
 * @description 热门影视剧
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotMoviesVo implements Serializable {

    /**
     * 热门Id
     */
    private String hotId;

    /**
     * 影视剧Id
     */
    private String movieId;

    /**
     * 影视剧名称
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    /**
     * 导演
     */
    private String director;

    /**
     * 电影/电视剧 1 是电影，0 是电视剧
     */
    private Integer type;

    /**
     * 首映时间
     */
    private String premiere;

    /**
     * 简介
     */
    private String introduction;
}
