package com.kanguan.service;

import com.kanguan.entity.po.Resource;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/22 20:58
 * @description 资源管理服务成接口
 */
public interface ResourceService {

    /**
     * 添加资源
     *
     * @param resource 资源
     * @return Boolean
     */
    Boolean addResource(Resource resource);

    /**
     * 通过资源名称查看是否已经存在
     *
     * @param name 资源名称
     * @return Boolean
     */
    Boolean isExistInDbByName(String name);

    /**
     * 通过对象获取资源
     *
     * @param object 资源对象
     * @return List<Resource>
     */
    List<Resource> getResourceByObject(String object);

    /**
     * 通过Id获取资源
     *
     * @param resourceId 资源Id
     * @return Resource
     */
    Resource getResourceById(String resourceId);
}
