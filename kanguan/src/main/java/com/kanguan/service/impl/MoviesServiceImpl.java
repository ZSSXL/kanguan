package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Movies;
import com.kanguan.mapper.MoviesMapper;
import com.kanguan.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
