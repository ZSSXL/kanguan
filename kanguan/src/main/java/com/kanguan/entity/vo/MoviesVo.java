package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author ZSS
 * @date 2020/3/19 21:09
 * @description 影视剧添加实体
 */
@Data
public class MoviesVo {

    /**
     * 影视剧名称
     */
    @NotEmpty
    private String name;

    /**
     * 导演
     */
    @NotEmpty
    private String director;

    /**
     * 编剧
     */
    @NotEmpty
    private String scenarist;

    /**
     * 主演
     */
    private String staring;

    /**
     * 类型，如：喜剧/剧情/恐怖/科幻
     */
    private String style;

    /**
     * 电影/电视剧 1 是电影，0 是电视剧
     */
    private Integer type;

    /**
     * 国家和地区
     */
    private String countryRegion;

    /**
     * 首映时间
     */
    private String premiere;

    /**
     * 上映时间
     */
    private String release;

    /**
     * 片长
     */
    private String length;

    /**
     * 又名
     */
    private String anotherName;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 简介
     */
    private String introduction;
}
