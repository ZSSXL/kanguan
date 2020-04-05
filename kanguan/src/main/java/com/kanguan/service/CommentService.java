package com.kanguan.service;

import com.kanguan.entity.po.Comment;
import com.kanguan.entity.vo.CommentUserVo;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/4/5 21:44
 * @description 评论服务层接口
 */
public interface CommentService {

    /**
     * 创建评论
     *
     * @param comment 评论
     * @return Boolean
     */
    Boolean createComment(Comment comment);

    /**
     * 获取评论信息
     *
     * @param targetId 评论对象
     * @return List<Comment>
     */
    List<CommentUserVo> getCommentsByTargetId(String targetId);
}
