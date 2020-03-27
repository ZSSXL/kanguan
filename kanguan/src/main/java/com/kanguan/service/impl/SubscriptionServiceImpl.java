package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Subscription;
import com.kanguan.entity.vo.SubResourceVo;
import com.kanguan.mapper.ResourceMapper;
import com.kanguan.mapper.SubscriptionMapper;
import com.kanguan.service.SubscriptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/26 22:40
 * @description 订阅服务层方法实现
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;
    private final ResourceMapper resourceMapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionMapper subscriptionMapper, ResourceMapper resourceMapper) {
        this.subscriptionMapper = subscriptionMapper;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Boolean addSubscription(Subscription subscription) {
        int insert = subscriptionMapper.insert(subscription);
        return insert == 1;
    }

    @Override
    public Boolean isExistInDb(String subObject) {
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        wrapper.eq("sub_object", subObject);
        Subscription subscription = subscriptionMapper.selectOne(wrapper);
        return subscription != null;
    }

    @Override
    public List<SubResourceVo> getSubBySubscriber(String subscriber) {
        List<SubResourceVo> subResourceVoList = new ArrayList<>();
        List<SubResourceVo> subResourceVos = subscriptionMapper.selectSubBySubscriber(subscriber);
        for (SubResourceVo sr : subResourceVos) {
            String updateTimeByObject = resourceMapper.getUpdateTimeByObject(sr.getSubObject());
            if (StringUtils.isEmpty(updateTimeByObject)) {
                sr.setUpdateTime("null");
            } else {
                sr.setUpdateTime(updateTimeByObject);
            }
            subResourceVoList.add(sr);
        }
        return subResourceVoList;
    }

    @Override
    public Boolean deleteSubById(String subId, String subscriber) {
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        wrapper.eq("sub_id", subId)
                .eq("subscriber", subscriber);
        int delete = subscriptionMapper.delete(wrapper);
        return delete == 1;
    }
}
