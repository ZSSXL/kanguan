package com.kanguan.service;

import com.kanguan.entity.po.Request;

import java.util.List;

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

    /**
     * 获取资源请求详情
     *
     * @param requester 请求者
     * @return List<Request>
     */
    List<Request> getRequestByRequester(String requester);

    /**
     * 删除请求
     *
     * @param requester 请求者
     * @param requestId 请求Id
     * @return Boolean
     */
    Boolean deleteRequest(String requester, String requestId);

    /**
     * 获取未回复的请求的数量
     *
     * @return Integer
     */
    Integer getUnansweredCount();

    /**
     * 根据状态获取所有请求
     *
     * @param exist 响应状况
     * @return List<Request>
     */
    List<Request> getAllRequest(String exist);

    /**
     * 通过Id判断请求是否存在
     *
     * @param requestId 请求Id
     * @return Boolean
     */
    Boolean isExistInDbById(String requestId);

    /**
     * 更新请求的状态
     *
     * @param requestId 请求Id
     * @param movieId   电影Id
     * @return Boolean
     */
    Boolean updateRequestExist(String requestId, String movieId);
}
