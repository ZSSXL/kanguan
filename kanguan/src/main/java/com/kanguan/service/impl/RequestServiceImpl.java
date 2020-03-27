package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Request;
import com.kanguan.mapper.RequestMapper;
import com.kanguan.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Boolean isExistInDb(String requester, String name) {
        QueryWrapper<Request> wrapper = new QueryWrapper<>();
        wrapper.eq("requester", requester)
                .like("name", name);
        Request request = requestMapper.selectOne(wrapper);
        return request != null;
    }

    @Override
    public List<Request> getRequestByRequester(String requester) {
        QueryWrapper<Request> wrapper = new QueryWrapper<>();
        wrapper.eq("requester", requester);
        return requestMapper.selectList(wrapper);
    }

    @Override
    public Boolean deleteRequest(String requester, String requestId) {
        QueryWrapper<Request> wrapper = new QueryWrapper<>();
        wrapper.eq("requester", requester)
                .eq("request_id", requestId);
        int delete = requestMapper.delete(wrapper);
        return delete == 1;
    }
}
