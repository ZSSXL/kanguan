package com.kanguan.service;

import com.kanguan.entity.po.Hot;
import com.kanguan.entity.vo.HotMoviesVo;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/25 11:05
 * @description 热门推荐服务层接口
 */
public interface HotService {

    /**
     * 查看热门数量
     *
     * @param type 类型 电影/电视剧
     * @return Boolean
     */
    Boolean checkCountByType(Integer type);

    /**
     * 查看是否已经上了热门
     *
     * @param object 对象
     * @return Boolean
     */
    Boolean isExistInDb(String object);

    /**
     * 添加热门推荐
     *
     * @param hot 热门实体
     * @return Boolean
     */
    Boolean createHot(Hot hot);

    /**
     * 删除热门
     *
     * @param hotId 热门Id
     * @return Boolean
     */
    Boolean deleteHotById(String hotId);

    /**
     * 获取热门影视剧
     *
     * @param type 类型
     * @return List<Movies>
     */
    List<HotMoviesVo> getHotByType(Integer type);
}
