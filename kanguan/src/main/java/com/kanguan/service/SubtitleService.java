package com.kanguan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.vo.SubtitleMoviesVo;

/**
 * @author ZSS
 * @date 2020/4/7 0:12
 * @description 字幕服务层接口
 */
public interface SubtitleService {

    /**
     * 根据类型获取字幕梗概
     *
     * @param type 字幕类型
     * @param page 分页信息
     * @return IPage<String>
     */
    IPage<SubtitleMoviesVo> getSubtitleMainMessage(String type, IPage<SubtitleMoviesVo> page);
}
