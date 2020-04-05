package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Comment;
import com.kanguan.entity.vo.CommentUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/16 16:34
 * @description 评论 mapper
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取评论信息
     *
     * @param targetId 评论对象
     * @return List<CommentUserVo>
     */
    List<CommentUserVo> getCommentUserVoByTargetId(String targetId);
}
