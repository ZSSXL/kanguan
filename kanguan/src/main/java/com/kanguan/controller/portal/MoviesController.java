package com.kanguan.controller.portal;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Movies;
import com.kanguan.entity.po.Resource;
import com.kanguan.entity.vo.MoviesResourceVo;
import com.kanguan.service.MoviesService;
import com.kanguan.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/23 21:31
 * @description 影视剧控制器
 */
@Slf4j
@RestController
@RequestMapping("/movies")
public class MoviesController extends BaseController {

    private final MoviesService moviesService;
    private final ResourceService resourceService;

    @Autowired
    public MoviesController(MoviesService moviesService, ResourceService resourceService) {
        this.moviesService = moviesService;
        this.resourceService = resourceService;
    }

    /**
     * 获取所有的电影资源
     *
     * @param page 第几页
     * @param size 每页大小
     * @return ServerResponse<IPage < Movies>>
     */
    @GetMapping("/movie")
    @RequiredPermission
    public ServerResponse<IPage<Movies>> getAllMovie(@RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page
            , @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {
        IPage<Movies> allMovies = moviesService.getAllMovies(Const.type.MOVIE, page, size);
        return ServerResponse.createBySuccess(allMovies);
    }

    /**
     * 获取所有的电视剧资源
     *
     * @param page 第几页
     * @param size 每页大小
     * @return ServerResponse<IPage < Movies>>
     */
    @GetMapping("/tv")
    @RequiredPermission
    public ServerResponse<IPage<Movies>> getAllTv(@RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page
            , @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {
        IPage<Movies> allMovies = moviesService.getAllMovies(Const.type.TV, page, size);
        return ServerResponse.createBySuccess(allMovies);
    }

    /**
     * 通过Id获取资源
     *
     * @param movieId 影视剧Id
     * @return ServerResponse<Movies>
     */
    @GetMapping("/{movieId}")
    public ServerResponse<MoviesResourceVo> getMoviesById(@PathVariable("movieId") String movieId) {
        if (StringUtils.isEmpty(movieId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Movies movies = moviesService.getMoviesById(movieId);
            if (movies == null) {
                return ServerResponse.createByErrorMessage("查询失败, 请重新尝试...");
            } else {
                List<Resource> resourceList = resourceService.getResourceByObject(movieId);
                if (resourceList == null) {
                    return ServerResponse.createByErrorMessage("查询失败, 请重新尝试...");
                } else {
                    MoviesResourceVo build = MoviesResourceVo.builder()
                            .movies(movies)
                            .resourceList(resourceList)
                            .build();
                    return ServerResponse.createBySuccess(build);
                }
            }
        }
    }

}
