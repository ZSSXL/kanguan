package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Comment;
import com.kanguan.entity.vo.CommentUserVo;
import com.kanguan.entity.vo.CommentVo;
import com.kanguan.service.CommentService;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.TokenUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZSS
 * @date 2020/4/5 21:43
 * @description 用户评论控制器
 */
@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {

    private final CommentService commentService;
    private final TokenUtil tokenUtil;

    @Autowired
    public CommentController(CommentService commentService, TokenUtil tokenUtil) {
        this.commentService = commentService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 创建评论
     *
     * @param commentVo 评论Vo
     * @param token     用户token
     * @param result    错误结果
     * @return String
     */
    @PostMapping
    @RequiredPermission
    public ServerResponse<String> createComment(@RequestBody @Valid CommentVo commentVo, @RequestHeader("token") String token, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            String commentator = tokenUtil.getClaim(token, "userId").asString();
            Comment build = Comment.builder()
                    .commentId(UUIDUtil.getId())
                    .commentator(commentator)
                    .content(commentVo.getContent())
                    .targetId(commentVo.getTargetId())
                    .createTime(TimeUtil.getTimestamp())
                    .updateTime(TimeUtil.getTimestamp())
                    .build();
            Boolean commentSave = commentService.createComment(build);
            if (commentSave) {
                return ServerResponse.createBySuccess();
            } else {
                return ServerResponse.createBySuccessMessage("评论失败，请刷新重试");
            }
        }
    }

    /**
     * 获取评论
     *
     * @param targetId 评论对象
     * @return List<Comment>
     */
    @GetMapping("/{targetId}")
    @RequiredPermission
    public ServerResponse<List<CommentUserVo>> getCommentByTargetId(@PathVariable("targetId") String targetId) {
        if (StringUtils.isEmpty(targetId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            List<CommentUserVo> commentList = commentService.getCommentsByTargetId(targetId);
            if (commentList == null) {
                return ServerResponse.createByErrorMessage("查询失败, 请刷新重试");
            } else {
                return ServerResponse.createBySuccess(commentList);
            }
        }
    }
}
