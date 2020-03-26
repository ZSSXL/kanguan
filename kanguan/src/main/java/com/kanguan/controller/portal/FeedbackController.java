package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Feedback;
import com.kanguan.service.FeedbackService;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.TokenUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/26 21:33
 * @description 反馈控制器
 */
@Slf4j
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

    private final FeedbackService feedbackService;
    private final TokenUtil tokenUtil;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, TokenUtil tokenUtil) {
        this.feedbackService = feedbackService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 添加反馈
     *
     * @param content 反馈内容
     * @param token   用户token
     * @return ServerResponse<String>
     */
    @PostMapping
    @RequiredPermission
    public ServerResponse<String> createFeedback(@RequestBody String content, @RequestHeader("token") String token) {
        if (StringUtils.isEmpty(content)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            String userId = tokenUtil.getClaim(token, "userId").asString();
            Feedback build = Feedback.builder()
                    .feedbackId(UUIDUtil.getId())
                    .feedbackPerson(userId)
                    .content(content)
                    .read(0)
                    .createTime(TimeUtil.getTimestamp())
                    .updateTime(TimeUtil.getTimestamp())
                    .build();
            try {
                Boolean feedback = feedbackService.createFeedback(build);
                if (feedback) {
                    return ServerResponse.createBySuccessMessage("反馈成功");
                } else {
                    return ServerResponse.createByErrorMessage("反馈失败");
                }
            } catch (Exception e) {
                log.error("create feedback has unknown error ", e);
                return ServerResponse.createByErrorMessage("提交反馈发生未知异常");
            }
        }
    }

    /**
     * 获取个人反馈
     *
     * @param token 用户token
     * @return ServerResponse<List < Feedback>>
     */
    @GetMapping
    @RequiredPermission
    public ServerResponse<List<Feedback>> getFeedback(@RequestHeader("token") String token) {
        String personal = tokenUtil.getClaim(token, "userId").asString();
        List<Feedback> feedbackList = feedbackService.getFeedbackByPersonal(personal);
        if (feedbackList == null) {
            return ServerResponse.createByErrorMessage("获取失败，请刷新以重新尝试");
        } else {
            return ServerResponse.createBySuccess(feedbackList);
        }
    }
}
