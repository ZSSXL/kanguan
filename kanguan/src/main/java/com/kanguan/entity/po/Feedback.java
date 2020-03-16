package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 15:38
 * @description 反馈表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_feedback")
public class Feedback extends Model<Feedback> implements Serializable {

    /**
     * 反馈Id
     */
    @TableId
    private String feedbackId;

    /**
     * 反馈内容
     */
    private String content;

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
        return feedbackId;
    }
}
