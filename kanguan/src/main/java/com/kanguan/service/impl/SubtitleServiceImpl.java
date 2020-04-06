package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.vo.SubtitleMoviesVo;
import com.kanguan.mapper.SubtitleMapper;
import com.kanguan.service.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
