package com.kanguan.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @date 2020/4/2 22:57
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 会员
     */
    private String member;

    /**
     * 创建时间
     */
    private String createTime;
}
