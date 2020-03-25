package com.kanguan.service.impl;

import com.kanguan.entity.po.Request;
import com.kanguan.mapper.RequestMapper;
import com.kanguan.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2020/3/25 22:47
 * @description 求资源服务层接口方法实现
 */
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestMapper requestMapper;

    @Autowired
    public RequestServiceImpl(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }

    @Override
    public Boolean createRequest(Request request) {
        int insert = requestMapper.insert(request);
        return insert == 1;
    }
}
