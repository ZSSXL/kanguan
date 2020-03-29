package com.kanguan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Movies;
import com.kanguan.entity.vo.SelectVo;

import java.util.List;

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

    /**
     * 通过Id删除影视剧
     *
     * @param movieId 影视剧
     * @return Boolean
     */
    Boolean deleteMoviesById(String movieId);

    /**
     * 通过Id获取资源
     *
     * @param movieId 影视剧Id
     * @return Movies
     */
    Movies getMoviesById(String movieId);

    /**
     * 检索影视剧
     *
     * @param name 影视剧名称
     * @return List<Movies>
     */
    List<Movies> searchMoviesByName(String name);

    /**
     * 检索影视剧
     *
     * @param select     检索田间
     * @param moviesPage 分页信息
     * @return IPage<Movies>
     */
    IPage<Movies> selectMovies(SelectVo select, IPage<Movies> moviesPage);
}
