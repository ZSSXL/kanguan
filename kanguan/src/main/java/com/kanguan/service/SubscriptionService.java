package com.kanguan.service;

import com.kanguan.entity.po.Subscription;
import com.kanguan.entity.vo.SubResourceVo;

import java.util.List;

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

    /**
     * 获取个人订阅
     *
     * @param subscriber 订阅人
     * @return List<SubResourceVo>
     */
    List<SubResourceVo> getSubBySubscriber(String subscriber);

    /**
     * 删除订阅
     *
     * @param subId      订阅Id
     * @param subscriber 订阅人
     * @return Boolean
     */
    Boolean deleteSubById(String subId, String subscriber);
}
