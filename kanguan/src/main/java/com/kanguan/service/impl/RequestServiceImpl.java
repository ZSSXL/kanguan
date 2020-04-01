package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kanguan.common.Const;
import com.kanguan.entity.po.Request;
import com.kanguan.mapper.RequestMapper;
import com.kanguan.service.RequestService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public Integer getUnansweredCount() {
        QueryWrapper<Request> wrapper = new QueryWrapper<>();
        wrapper.eq("exist", "0");
        return requestMapper.selectCount(wrapper);
    }

    @Override
    public List<Request> getAllRequest(String exist) {
        QueryWrapper<Request> wrapper = new QueryWrapper<>();
        if (StringUtils.equals(exist, Const.exist.NO)) {
            wrapper.eq("exist", Const.exist.NO);
        } else if (StringUtils.equals(exist, Const.exist.YES)) {
            wrapper.ne("exist", Const.exist.NO);
        }
        return requestMapper.selectList(wrapper);
    }

    @Override
    public Boolean isExistInDbById(String requestId) {
        Request request = requestMapper.selectById(requestId);
        return request != null;
    }

    @Override
    public Boolean updateRequestExist(String requestId, String movieId) {
        UpdateWrapper<Request> wrapper = new UpdateWrapper<>();
        wrapper.eq("request_id", requestId)
                .set("exist", movieId);
        int update = requestMapper.update(null, wrapper);
        return update == 1;
    }
}
