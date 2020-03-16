package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:34
 * @description 评论 mapper
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
