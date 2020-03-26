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

    /**
     * 查看是否已经存在该请求
     *
     * @param requester 请求者
     * @param name      资源名称
     * @return Boolean
     */
    Boolean isExistInDb(String requester, String name);
}
