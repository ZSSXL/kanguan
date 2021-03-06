package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:37
 * @description 资源 mapper
 */
@Mapper
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 获取最近更新时间
     *
     * @param object 资源对象
     * @return String
     */
    String getUpdateTimeByObject(String object);
}
