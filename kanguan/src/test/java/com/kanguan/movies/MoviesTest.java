package com.kanguan.movies;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanguan.BaseTest;
import com.kanguan.common.Const;
import com.kanguan.entity.po.Movies;
import com.kanguan.mapper.MoviesMapper;
import com.kanguan.service.MoviesService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/19 21:37
 * @description 影视剧测试
 */
public class MoviesTest extends BaseTest {

    @Autowired
    private MoviesMapper moviesMapper;

    @Autowired
    private MoviesService moviesService;

    @Test
    public void selectMoviesTest() {
        Movies movies = new Movies();
        List<Movies> moviesList = movies.selectAll();
        for (Movies m : moviesList) {
            System.out.println(m);
        }
    }

    @Test
    public void selectMoviesPageTest() {
        IPage<Movies> moviesIPage = new Page<>(2, 3);
        QueryWrapper<Movies> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        IPage<Movies> iPage = moviesMapper.selectPage(moviesIPage, wrapper);
        System.out.println(JSON.toJSONString(iPage));
        System.out.println("===================================================");
        List<Movies> records = iPage.getRecords();
        for (Movies m : records) {
            System.out.println(m);
        }
    }

    @Test
    public void selectPageServiceTest() {
        Integer page = 1;
        Integer size = 2;
        IPage<Movies> allMovies = moviesService.getAllMovies(Const.type.MOVIE, page, size);
        System.out.println(allMovies.getTotal());
        System.out.println(JSON.toJSONString(allMovies));
    }
}
