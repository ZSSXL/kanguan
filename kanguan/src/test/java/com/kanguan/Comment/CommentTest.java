package com.kanguan.Comment;

import com.kanguan.BaseTest;
import com.kanguan.entity.vo.CommentUserVo;
import com.kanguan.mapper.CommentMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/4/5 22:47
 * @description 评论测试
 */
public class CommentTest extends BaseTest {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 获取评论信息
     */
    @Test
    public void getComments() {
        String targetId = "73f10c544e054e789ff37bf88b55b99a";
        List<CommentUserVo> commentUserVoByTargetId = commentMapper.getCommentUserVoByTargetId(targetId);
        for (CommentUserVo cuv : commentUserVoByTargetId) {
            System.out.println(cuv);
        }
    }

}
