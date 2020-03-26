package com.kanguan.service;

import com.kanguan.entity.po.Subscription;

/**
 * @author ZSS
 * @date 2020/3/26 22:39
 * @description 订阅服务层接口
 */
public interface SubscriptionService {

    /**
     * 添加订阅
     *
     * @param subscription 订阅实体
     * @return Boolean
     */
    Boolean addSubscription(Subscription subscription);

    /**
     * 已经订阅
     *
     * @param subObject 订阅对象
     * @return Boolean
     */
    Boolean isExistInDb(String subObject);
}
