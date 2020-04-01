package com.kanguan.controller.backend;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.entity.po.Movies;
import com.kanguan.entity.po.Request;
import com.kanguan.entity.vo.ResponseVo;
import com.kanguan.service.MoviesService;
import com.kanguan.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/26 16:07
 * @description admin 求资源控制器
 */
@RestController("adminRequestController")
@RequestMapping("/backend/request")
public class RequestController {

    private final RequestService requestService;
    private final MoviesService moviesService;

    @Autowired
    public RequestController(RequestService requestService, MoviesService moviesService) {
        this.requestService = requestService;
        this.moviesService = moviesService;
    }

    /**
     * 获取未回复的请求数量
     *
     * @return ServerResponse<Integer>
     */
    @GetMapping
    @AdminExamine
    public ServerResponse<Integer> getUnansweredCount() {
        Integer count = requestService.getUnansweredCount();
        if (count == null) {
            return ServerResponse.createByErrorMessage("查询出错");
        } else {
            return ServerResponse.createBySuccess(count);
        }
    }

    /**
     * 获取所有的请求
     *
     * @return ServerResponse<List < Request>>
     */
    @GetMapping("/{exist}")
    @AdminExamine
    public ServerResponse<List<Request>> getAllRequest(@PathVariable("exist") String exist) {
        List<Request> requestList = requestService.getAllRequest(exist);
        if (requestList == null) {
            return ServerResponse.createByErrorMessage("查询出错");
        } else {
            return ServerResponse.createBySuccess(requestList);
        }
    }

    /**
     * 响应请求
     *
     * @param responseVo 响应Vo
     * @param result     错误结果
     * @return ServerResponse<String>
     */
    @PutMapping
    @AdminExamine
    public ServerResponse<String> response(@RequestBody @Valid ResponseVo responseVo, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Movies movies = moviesService.getMoviesByName(responseVo.getMoviesName());
            if (movies == null) {
                return ServerResponse.createByErrorMessage("该电影还未收录，请检查是否电影名填写正确");
            } else {
                Boolean existInDb = requestService.isExistInDbById(responseVo.getRequestId());
                if (!existInDb) {
                    return ServerResponse.createByErrorMessage("啊哦，发生错误了，请重新尝试");
                } else {
                    Boolean updateResult = requestService.updateRequestExist(responseVo.getRequestId(), movies.getMovieId());
                    if (updateResult) {
                        return ServerResponse.createBySuccess();
                    } else {
                        return ServerResponse.createByError();
                    }
                }
            }
        }
    }
}
