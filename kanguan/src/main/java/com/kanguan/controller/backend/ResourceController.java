package com.kanguan.controller.backend;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.entity.po.Movies;
import com.kanguan.entity.po.Resource;
import com.kanguan.entity.vo.MoviesResourceVo;
import com.kanguan.entity.vo.ResourceVo;
import com.kanguan.service.MoviesService;
import com.kanguan.service.ResourceService;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/22 20:57
 * @description 资源管理
 */
@Slf4j
@RestController("adminResourceController")
@RequestMapping("/backend/resource")
public class ResourceController {

    private final ResourceService resourceService;
    private final MoviesService moviesService;

    @Autowired
    public ResourceController(ResourceService resourceService, MoviesService moviesService) {
        this.resourceService = resourceService;
        this.moviesService = moviesService;
    }

    /**
     * 添加资源
     *
     * @param resourceVo 资源实体Vo
     * @param result     错误结果
     * @return ServerResponse<String>
     */
    @PostMapping
    @AdminExamine
    public ServerResponse<String> addResource(@RequestBody @Valid ResourceVo resourceVo, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Boolean existInDbByName = resourceService.isExistInDbByName(resourceVo.getName());
            if (existInDbByName) {
                return ServerResponse.createByErrorMessage("该资源已存在...");
            } else {
                String format = resourceVo.getName().substring(resourceVo.getName().lastIndexOf(".") + 1);
                try {
                    Resource build = Resource.builder()
                            .resourceId(UUIDUtil.getId())
                            .name(resourceVo.getName())
                            .bigness(resourceVo.getBigness())
                            .downloadLink(resourceVo.getDownloadLink())
                            .episode(resourceVo.getEpisode())
                            .format(format)
                            .object(resourceVo.getObject())
                            .createTime(TimeUtil.getTimestamp())
                            .updateTime(TimeUtil.getTimestamp())
                            .build();
                    Boolean addResult = resourceService.addResource(build);
                    if (addResult) {
                        return ServerResponse.createBySuccess();
                    } else {
                        return ServerResponse.createByError();
                    }
                } catch (Exception e) {
                    log.error("add resource has unknown error " + e);
                    return ServerResponse.createByErrorMessage("添加资源失败,发生未知异常...");
                }
            }
        }
    }

    /**
     * 获取影视剧资源详情
     *
     * @param movieId 影视剧Id
     * @return ServerResponse<MoviesResourceVo>
     */
    @GetMapping("/{movieId}")
    @AdminExamine
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
