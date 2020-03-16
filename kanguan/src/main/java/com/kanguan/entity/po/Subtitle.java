package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 15:38
 * @description 字母表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kg_subtitle")
public class Subtitle extends Model<Subtitle> implements Serializable {

    /**
     * 字幕Id
     */
    @TableId
    private String subtitleId;

    /**
     * 字幕名称
     */
    private String name;

    /**
     * 语言
     */
    private String language;

    /**
     * 格式
     */
    private String format;

    /**
     * 集数
     */
    private String episode;

    /**
     * 下载地址
     */
    private String download;

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
        return subtitleId;
    }
}
