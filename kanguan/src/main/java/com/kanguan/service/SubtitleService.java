package com.kanguan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Subtitle;
import com.kanguan.entity.vo.SubtitleMoviesVo;

import java.util.List;

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

    /**
     * 添加字幕
     *
     * @param subtitle 字幕
     * @return Boolean
     */
    Boolean addSubtitleToTarget(Subtitle subtitle);

    /**
     * 删除字幕
     *
     * @param subtitleId 字幕Id
     * @return Boolean
     */
    Boolean deleteSubtitleById(String subtitleId);

    /**
     * 获取字幕数据
     *
     * @param targetId 目标对象
     * @return List<Subtitle>
     */
    List<Subtitle> getSubtitleByTargetId(String targetId);

    /**
     * 是否已存在
     *
     * @param subtitleName 字幕名称
     * @return Boolean
     */
    Boolean existInDb(String subtitleName);
}
