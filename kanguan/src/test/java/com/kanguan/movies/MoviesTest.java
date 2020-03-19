package com.kanguan.movies;

import com.kanguan.BaseTest;
import com.kanguan.entity.po.Movies;
import org.junit.Test;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/19 21:37
 * @description 影视剧测试
 */
public class MoviesTest extends BaseTest {

    @Test
    public void selectMoviesTest() {
        Movies movies = new Movies();
        List<Movies> moviesList = movies.selectAll();
        for (Movies m : moviesList) {
            System.out.println(m);
        }
    }
}
