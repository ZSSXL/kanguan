package com.kanguan.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @date 2020/4/5 22:41
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUserVo {
    /**
     * 评论Id
     */
    private String commentId;

    /**
     * 评论人名称
     */
    private String commentName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String createTime;
}
