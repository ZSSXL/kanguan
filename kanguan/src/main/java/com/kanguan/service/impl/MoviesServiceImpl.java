package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanguan.common.Const;
import com.kanguan.entity.po.Movies;
import com.kanguan.mapper.MoviesMapper;
import com.kanguan.service.MoviesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/19 20:49
 * @description 影视剧服务层接口方法实现
 */
@Service
public class MoviesServiceImpl implements MoviesService {

    private final MoviesMapper moviesMapper;

    @Autowired
    public MoviesServiceImpl(MoviesMapper moviesMapper) {
        this.moviesMapper = moviesMapper;
    }

    @Override
    public Boolean isExistInDb(String name) {
        QueryWrapper<Movies> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Movies movies = moviesMapper.selectOne(wrapper);
        return movies != null;
    }

    @Override
    public Boolean createMovies(Movies movies) {
        int insert = moviesMapper.insert(movies);
        return insert >= 1;
    }

    @Override
    public IPage<Movies> getAllMovies(String type, Integer page, Integer size) {
        IPage<Movies> moviesPage = new Page<>(page, size);
        QueryWrapper<Movies> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (StringUtils.equals(type, Const.type.MOVIE)) {
            wrapper.eq("type", Const.type.MOVIE);
        } else {
            wrapper.eq("type", Const.type.TV);
        }
        return moviesMapper.selectPage(moviesPage, wrapper);
    }

    @Override
    public Boolean deleteMoviesById(String movieId) {
        int result = moviesMapper.deleteById(movieId);
        return result == 1;
    }

    @Override
    public Movies getMoviesById(String movieId) {
        return moviesMapper.selectById(movieId);
    }

    @Override
    public List<Movies> searchMoviesByName(String name) {
        QueryWrapper<Movies> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return moviesMapper.selectList(wrapper);
    }

}
