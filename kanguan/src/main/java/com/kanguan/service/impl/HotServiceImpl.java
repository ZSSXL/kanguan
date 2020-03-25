package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.common.Const;
import com.kanguan.entity.po.Hot;
import com.kanguan.entity.vo.HotMoviesVo;
import com.kanguan.mapper.HotMapper;
import com.kanguan.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/25 11:06
 * @description 热门推荐服务层接口方法实现
 */
@Service
public class HotServiceImpl implements HotService {

    private final HotMapper hotMapper;

    @Autowired
    public HotServiceImpl(HotMapper hotMapper) {
        this.hotMapper = hotMapper;
    }

    @Override
    public Boolean checkCountByType(Integer type) {
        QueryWrapper<Hot> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        Integer integer = hotMapper.selectCount(wrapper);
        return integer < Const.DEFAULT_RECOMMENDED_QUANTITY;
    }

    @Override
    public Boolean isExistInDb(String object) {
        QueryWrapper<Hot> wrapper = new QueryWrapper<>();
        wrapper.eq("object", object);
        Hot hot = hotMapper.selectOne(wrapper);
        return hot != null;
    }

    @Override
    public Boolean createHot(Hot hot) {
        int insert = hotMapper.insert(hot);
        return insert == 1;
    }

    @Override
    public Boolean deleteHotById(String hotId) {
        int result = hotMapper.deleteById(hotId);
        return result == 1;
    }

    @Override
    public List<HotMoviesVo> getHotByType(Integer type) {
        return hotMapper.selectHotByType(type);
    }
}
