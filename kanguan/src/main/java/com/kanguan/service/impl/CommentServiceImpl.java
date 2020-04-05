package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Comment;
import com.kanguan.entity.vo.CommentUserVo;
import com.kanguan.mapper.CommentMapper;
import com.kanguan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/4/5 21:44
 * @description 评论服务层接口方法实现
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Boolean createComment(Comment comment) {
        int insert = commentMapper.insert(comment);
        return insert == 1;
    }

    @Override
    public List<CommentUserVo> getCommentsByTargetId(String targetId) {
        return commentMapper.getCommentUserVoByTargetId(targetId);
    }
}
