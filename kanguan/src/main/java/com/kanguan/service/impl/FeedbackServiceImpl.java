package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanguan.entity.po.Feedback;
import com.kanguan.mapper.FeedbackMapper;
import com.kanguan.service.FeedbackService;
import com.kanguan.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/26 21:30
 * @description 反馈服务层接口方法实现
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackServiceImpl(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public Boolean createFeedback(Feedback feedback) {
        int insert = feedbackMapper.insert(feedback);
        return insert == 1;
    }

    @Override
    public IPage<Feedback> getUnreadFeedback(Integer page, Integer size) {
        IPage<Feedback> feedbackPage = new Page<>(page, size);
        QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
        wrapper.eq("read", 0)
                .orderByDesc("create_time");
        return feedbackMapper.selectPage(feedbackPage, wrapper);
    }

    @Override
    public Boolean changeFeedbackReadById(String feedbackId) {
        Feedback feedback = feedbackMapper.selectById(feedbackId);
        if (feedback == null) {
            return false;
        } else {
            feedback.setRead(1);
            feedback.setUpdateTime(TimeUtil.getTimestamp());
            int i = feedbackMapper.updateById(feedback);
            return i == 1;
        }
    }

    @Override
    public List<Feedback> getFeedbackByPersonal(String person) {
        QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
        wrapper.eq("feedback_person", person);
        return feedbackMapper.selectList(wrapper);
    }
}
