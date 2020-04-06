package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Subtitle;
import com.kanguan.entity.vo.SubtitleMoviesVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:38
 * @description 字幕 mapper
 */
@Mapper
@Repository
public interface SubtitleMapper extends BaseMapper<Subtitle> {

    IPage<SubtitleMoviesVo> selectSubtitleMainMessageByMoviesType(IPage<SubtitleMoviesVo> page, String type);
}
