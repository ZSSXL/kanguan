package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:35
 * @description 反馈 mapper
 */
@Mapper
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {
}
