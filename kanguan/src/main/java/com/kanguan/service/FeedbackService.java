package com.kanguan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Feedback;
import com.kanguan.entity.vo.FeedbackAccountVo;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/26 21:29
 * @description 反馈服务层接口
 */
public interface FeedbackService {

    /**
     * 添加反馈
     *
     * @param feedback 反馈
     * @return Boolean
     */
    Boolean createFeedback(Feedback feedback);

    /**
     * 获取未读反馈
     *
     * @param read 阅读状态
     * @param page 当前页
     * @param size 每页大小
     * @return IPage<Feedback>P
     */
    IPage<Feedback> getUnreadFeedback(String read, Integer page, Integer size);

    /**
     * 改变反馈已读状态
     *
     * @param feedbackId 反馈Id
     * @return Boolean
     */
    Boolean changeFeedbackReadById(String feedbackId);

    /**
     * 获取个人的反馈记录
     *
     * @param person 个人
     * @return List<Feedback>
     */
    List<Feedback> getFeedbackByPersonal(String person);

    /**
     * 获取未读的反馈数量
     *
     * @return Integer
     */
    Integer getUnreadFeedbackCount();

    /**
     * 获取反馈&用户信息
     *
     * @param feedbackId 反馈Id
     * @return FeedbackAccountVo
     */
    FeedbackAccountVo getFeedbackAccountById(String feedbackId);
}
