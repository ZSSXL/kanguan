package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:39
 * @description 用户信息 mapper
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
