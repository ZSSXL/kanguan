package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Movies;
import com.kanguan.service.MoviesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/25 21:45
 * @description 搜索控制器
 */
@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    private final MoviesService moviesService;

    @Autowired
    public SearchController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    /**
     * 搜索影视剧
     *
     * @param name 影视剧名称
     * @return ServerResponse<List < Movies>>
     */
    @GetMapping("/{name}")
    @RequiredPermission
    public ServerResponse<List<Movies>> searchMovies(@PathVariable("name") String name) {
        if (StringUtils.isEmpty(name)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            List<Movies> moviesList = moviesService.searchMoviesByName(name);
            if (moviesList == null) {
                return ServerResponse.createByErrorMessage("没有该资源，留言期待");
            } else {
                return ServerResponse.createBySuccess(moviesList);
            }
        }
    }
}
