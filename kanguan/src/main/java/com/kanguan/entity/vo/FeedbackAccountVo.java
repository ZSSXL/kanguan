package com.kanguan.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @date 2020/4/1 23:05
 * @description 反馈 & 用户 Vo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackAccountVo {

    private String feedbackId;

    private String content;

    private String email;

    private String username;
}
