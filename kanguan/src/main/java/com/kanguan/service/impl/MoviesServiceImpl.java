package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanguan.common.Const;
import com.kanguan.entity.po.Movies;
import com.kanguan.entity.vo.SelectVo;
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

    @Override
    public IPage<Movies> selectMovies(SelectVo select, IPage<Movies> moviesPage) {
        QueryWrapper<Movies> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(select.getSelectType())) {
            wrapper.eq("type", select.getSelectType());
        }
        if (StringUtils.isNotEmpty(select.getSelectStyle())) {
            wrapper.like("style", select.getSelectStyle());
        }
        if (StringUtils.isNotEmpty(select.getSelectRegion())) {
            wrapper.like("country_region", select.getSelectRegion());
        }
        if (StringUtils.isNotEmpty(select.getSelectPremiere())) {
            // 多年代筛选
            switch (select.getSelectPremiere()) {
                case "2020":
                case "2019":
                    wrapper.like("premiere", select.getSelectPremiere());
                    break;
                case "2010年代":
                    wrapper.between("premiere", "2010", "2019");
                    break;
                case "2000年代":
                    wrapper.between("premiere", "2000", "2009");
                    break;
                case "90年代":
                    System.out.println("1990 - 1999");
                    wrapper.between("premiere", "1990", "1999");
                    break;
                case "80年代":
                    wrapper.between("premiere", "1980", "1989");
                    break;
                case "70年代":
                    wrapper.between("premiere", "1970", "1979");
                    break;
                case "60年代":
                    wrapper.between("premiere", "1960", "1969");
                    break;
                case "50年代":
                    wrapper.between("premiere", "1950", "1959");
                    break;
                default:
                    wrapper.like("premiere", "");
            }
        }
        wrapper.orderByDesc("create_time");
        return moviesMapper.selectPage(moviesPage, wrapper);
    }

    @Override
    public Movies getMoviesByName(String movieName) {
        QueryWrapper<Movies> wrapper = new QueryWrapper<>();
        wrapper.eq("name", movieName);
        return moviesMapper.selectOne(wrapper);
    }

}
