package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/25 10:53
 * @description 热门推荐
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_hot")
public class Hot extends Model<Hot> implements Serializable {

    /**
     * 热门Id
     */
    @TableId
    private String hotId;

    /**
     * 热门对象
     */
    private String object;

    /**
     * 电影/电视剧 1 是电影，0 是电视剧
     */
    private Integer type;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    @Override
    protected Serializable pkVal() {
        return hotId;
    }
}
