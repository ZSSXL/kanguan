package com.kanguan.feedback;

import com.kanguan.BaseTest;
import com.kanguan.entity.po.Feedback;
import com.kanguan.entity.vo.FeedbackAccountVo;
import com.kanguan.mapper.FeedbackMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/4/1 23:16
 * @description 反馈测试
 */
public class FeedbackTest extends BaseTest {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Test
    public void getAllFeedbackTest() {
        List<Feedback> feedbackList = feedbackMapper.selectList(null);
        for (Feedback fb : feedbackList) {
            System.out.println(fb);
        }
    }

    @Test
    public void getFeedbackAccountById() {
        /*
         * a5fabbd89c0d4105be2646b17a0e06d9
         * d537dc7449f940bdab59feb8a52c7b90
         * b15ffafe855a41e68bb58b9adab491de
         * */
        FeedbackAccountVo feedbackAccountVo = feedbackMapper.selectFeedbackAndAccountById("a5fabbd89c0d4105be2646b17a0e06d9");
        System.out.println(feedbackAccountVo);
    }
}
