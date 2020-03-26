package com.kanguan.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.entity.po.Feedback;
import com.kanguan.service.FeedbackService;
import com.kanguan.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @date 2020/3/26 21:46
 * @description 管理员 反馈控制器
 */
@Slf4j
@RestController("adminFeedbackController")
@RequestMapping("/backend/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * 获取未读反馈
     *
     * @param session 用户session
     * @return ServerResponse
     */
    @GetMapping
    public ServerResponse<IPage<Feedback>> getUnreadFeedback(HttpSession session
            , @RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page
            , @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            IPage<Feedback> unreadFeedback = feedbackService.getUnreadFeedback(page, size);
            if (unreadFeedback == null) {
                return ServerResponse.createByErrorMessage("获取反馈失败，请刷新重试");
            } else {
                return ServerResponse.createBySuccess(unreadFeedback);
            }
        }
    }

    /**
     * 改变反馈状态
     *
     * @param session    用户session
     * @param feedbackId 反馈Id
     * @return ServerResponse<String>
     */
    @PutMapping
    public ServerResponse<String> changeUnreadStatus(HttpSession session, @RequestBody String feedbackId) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            Boolean result = feedbackService.changeFeedbackReadById(feedbackId);
            if (result) {
                return ServerResponse.createBySuccess();
            } else {
                return ServerResponse.createByError();
            }
        }
    }

}
