package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 15:38
 * @description 评论表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_comment")
public class Comment extends Model<Comment> implements Serializable {

    /**
     * 评论Id
     */
    @TableId
    private String commentId;

    /**
     * 评论人
     */
    private String commentator;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论对象Id
     */
    private String targetId;

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
        return commentId;
    }
}
