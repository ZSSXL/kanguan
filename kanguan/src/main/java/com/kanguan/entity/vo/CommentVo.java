package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/4/5 21:47
 * @description 评论Vo
 */
@Data
public class CommentVo {

    /**
     * 评论对象
     */
    @NotEmpty
    private String targetId;

    /**
     * 评论内容
     */
    @NotEmpty
    private String content;
}
