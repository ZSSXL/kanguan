package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Resource;
import com.kanguan.mapper.ResourceMapper;
import com.kanguan.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2020/3/22 20:59
 * @description 资源服务层接口方法实现
 */
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMapper resourceMapper;

    @Autowired
    public ResourceServiceImpl(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Boolean addResource(Resource resource) {
        int insert = resourceMapper.insert(resource);
        return insert == 1;
    }

    @Override
    public Boolean isExistInDbByName(String name) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        Resource resource = resourceMapper.selectOne(wrapper);
        return resource != null;
    }
}
