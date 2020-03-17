package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Subscription;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:37
 * @description 订阅 mapper
 */
@Mapper
@Repository
public interface SubscriptionMapper extends BaseMapper<Subscription> {
}
