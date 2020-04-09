package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Subtitle;
import com.kanguan.entity.vo.SubtitleMoviesVo;
import com.kanguan.mapper.SubtitleMapper;
import com.kanguan.service.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/4/7 0:13
 * @description 字幕服务层接口方法实现
 */
@Service
public class SubtitleServiceImpl implements SubtitleService {

    private final SubtitleMapper subtitleMapper;

    @Autowired
    public SubtitleServiceImpl(SubtitleMapper subtitleMapper) {
        this.subtitleMapper = subtitleMapper;
    }

    @Override
    public IPage<SubtitleMoviesVo> getSubtitleMainMessage(String type, IPage<SubtitleMoviesVo> page) {
        return subtitleMapper.selectSubtitleMainMessageByMoviesType(page, type);
    }

    @Override
    public Boolean addSubtitleToTarget(Subtitle subtitle) {
        int insert = subtitleMapper.insert(subtitle);
        return insert == 1;
    }

    @Override
    public Boolean deleteSubtitleById(String subtitleId) {
        int i = subtitleMapper.deleteById(subtitleId);
        return i == 1;
    }

    @Override
    public List<Subtitle> getSubtitleByTargetId(String targetId) {
        QueryWrapper<Subtitle> wrapper = new QueryWrapper<>();
        wrapper.eq("target_id", targetId)
                .orderByAsc("episode");
        return subtitleMapper.selectList(wrapper);
    }

    @Override
    public Boolean existInDb(String subtitleName) {
        QueryWrapper<Subtitle> wrapper = new QueryWrapper<>();
        wrapper.eq("name", subtitleName);
        Subtitle subtitle = subtitleMapper.selectOne(wrapper);
        return subtitle != null;
    }
}
