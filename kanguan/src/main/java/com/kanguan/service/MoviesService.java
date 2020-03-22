package com.kanguan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Movies;

/**
 * @author ZSS
 * @date 2020/3/19 20:49
 * @description 影视剧服务层接口
 */
public interface MoviesService {

    /**
     * 检查是否存在该电影或电视剧
     *
     * @param name 电影/电视剧名称
     * @return Boolean
     */
    Boolean isExistInDb(String name);

    /**
     * 添加新的影视剧
     *
     * @param movies 影视剧实体
     * @return Boolean
     */
    Boolean createMovies(Movies movies);

    /**
     * 分页获取所有影视剧资源
     *
     * @param type 电影 1/电视剧 0
     * @param page 当前页
     * @param size 当前页大小
     * @return IPage<Movies>
     */
    IPage<Movies> getAllMovies(String type, Integer page, Integer size);

}
