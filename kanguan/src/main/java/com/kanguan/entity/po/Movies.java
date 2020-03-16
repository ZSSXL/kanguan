package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ZSS
 * @date 2020/3/16 15:37
 * @description 影视剧表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kg_movies")
public class Movies extends Model<Movies> implements Serializable {

    /**
     * 影视剧Id
     */
    @TableId
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
     * 编剧
     */
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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    @Override
    protected Serializable pkVal() {
        return movieId;
    }
}
