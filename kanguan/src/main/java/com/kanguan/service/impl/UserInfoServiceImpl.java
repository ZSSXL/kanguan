package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.common.Const;
import com.kanguan.entity.po.UserInfo;
import com.kanguan.mapper.UserInfoMapper;
import com.kanguan.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2020/3/17 22:13
 * @description 用户信息服务层接口方法实现
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public Boolean createUserInfo(UserInfo userInfo) {
        int insert = userInfoMapper.insert(userInfo);
        return insert == 1;
    }

    @Override
    public Integer getMemberCount() {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("member", Const.member.YES);
        return userInfoMapper.selectCount(wrapper);
    }
}
