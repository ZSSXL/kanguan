package com.kanguan.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.common.Const;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.entity.po.Feedback;
import com.kanguan.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 获取未读的反馈数量
     *
     * @return ServerResponse<Integer>
     */
    @GetMapping
    @AdminExamine
    public ServerResponse<Integer> getUnreadFeedbackCount() {
        Integer count = feedbackService.getUnreadFeedbackCount();
        if (count == null) {
            return ServerResponse.createByErrorMessage("查询出错");
        } else {
            return ServerResponse.createBySuccess(count);
        }
    }

    /**
     * 获取未读反馈
     *
     * @param page 当前页
     * @param size 每页大小
     * @return ServerResponse<IPage < Feedback>>
     */
    @GetMapping("/{read}")
    @AdminExamine
    public ServerResponse<IPage<Feedback>> getFeedback(@PathVariable("read") String read,
                                                       @RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page,
                                                       @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {
        IPage<Feedback> unreadFeedback = feedbackService.getUnreadFeedback(read, page, size);
        if (unreadFeedback == null) {
            return ServerResponse.createByErrorMessage("获取反馈失败，请刷新重试");
        } else {
            return ServerResponse.createBySuccess(unreadFeedback);
        }
    }

    /**
     * 改变反馈状态
     *
     * @param feedbackId 反馈Id
     * @return ServerResponse<String>
     */
    @PutMapping
    @AdminExamine
    public ServerResponse<String> changeUnreadStatus(@RequestBody String feedbackId) {
        Boolean result = feedbackService.changeFeedbackReadById(feedbackId);
        if (result) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByError();
        }
    }

}
