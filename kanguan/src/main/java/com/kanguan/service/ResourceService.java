package com.kanguan.service;

import com.kanguan.entity.po.Resource;

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
}
