package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 15:38
 * @description 资源表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_resource")
public class Resource extends Model<Resource> implements Serializable {

    /**
     * 资源Id
     */
    @TableId
    private String resourceId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 集数
     */
    private String episode;

    /**
     * 大小
     */
    private String bigness;

    /**
     * 格式
     */
    private String format;

    /**
     * 磁力链接
     */
    private String downloadLink;

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
        return resourceId;
    }
}
