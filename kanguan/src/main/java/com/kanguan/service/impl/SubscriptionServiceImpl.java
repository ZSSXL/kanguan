package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Subscription;
import com.kanguan.mapper.SubscriptionMapper;
import com.kanguan.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2020/3/26 22:40
 * @description 订阅服务层方法实现
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionMapper subscriptionMapper) {
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public Boolean addSubscription(Subscription subscription) {
        int insert = subscriptionMapper.insert(subscription);
        return insert == 1;
    }

    @Override
    public Boolean isExistInDb(String subObject) {
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        wrapper.eq("sub_obejct", subObject);
        Subscription subscription = subscriptionMapper.selectOne(wrapper);
        return subscription != null;
    }
}
