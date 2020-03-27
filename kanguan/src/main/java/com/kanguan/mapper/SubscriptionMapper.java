package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Subscription;
import com.kanguan.entity.vo.SubResourceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/16 16:37
 * @description 订阅 mapper
 */
@Mapper
@Repository
public interface SubscriptionMapper extends BaseMapper<Subscription> {

    /**
     * 查询订阅信息
     *
     * @param subscriber 订阅者
     * @return List<SubResourceVo>
     */
    List<SubResourceVo> selectSubBySubscriber(String subscriber);
}
