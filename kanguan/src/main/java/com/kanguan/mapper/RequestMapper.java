package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Request;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:36
 * @description 资源申请 mapper
 */
@Mapper
@Repository
public interface RequestMapper extends BaseMapper<Request> {
}
