package com.kanguan.service;

import com.kanguan.entity.po.UserInfo;

/**
 * @author ZSS
 * @date 2020/3/17 22:13
 * @description 用户信息服务层接口
 */
public interface UserInfoService {

    /**
     * 创建用户信息
     * @param userInfo 用户信息
     * @return Boolean
     */
    Boolean createUserInfo(UserInfo userInfo);

    /**
     * 获取会员用户数量
     *
     * @return Integer
     */
    Integer getMemberCount();
}
