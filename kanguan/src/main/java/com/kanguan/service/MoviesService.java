package com.kanguan.service;

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
}
