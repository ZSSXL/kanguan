package com.kanguan.service;

import com.kanguan.entity.po.Request;

/**
 * @author ZSS
 * @date 2020/3/25 22:46
 * @description 求资源服务层接口
 */
public interface RequestService {

    /**
     * 添加求资源
     *
     * @param request request
     * @return Boolean
     */
    Boolean createRequest(Request request);
}
