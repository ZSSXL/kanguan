package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Subtitle;
import com.kanguan.entity.vo.SubtitleMoviesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:38
 * @description 字幕 mapper
 */
@Mapper
@Repository
public interface SubtitleMapper extends BaseMapper<Subtitle> {

    /**
     * 获取字幕信息
     *
     * @param page 分页信息
     * @param type 影视剧类型
     * @return IPage<SubtitleMoviesVo>
     */
    IPage<SubtitleMoviesVo> selectSubtitleMainMessageByMoviesType(IPage<SubtitleMoviesVo> page, @Param("type") String type);
}
