package com.kanguan.controller.portal;

import com.kanguan.common.Const;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.vo.HotMoviesVo;
import com.kanguan.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/25 21:13
 * @description 热门推荐控制器
 */
@RestController
@RequestMapping("/hot")
public class HotController extends BaseController {

    private final HotService hotService;

    @Autowired
    public HotController(HotService hotService) {
        this.hotService = hotService;
    }

    /**
     * 获取热门电影
     *
     * @return ServerResponse<List<HotMoviesVo>>
     */
    @GetMapping("/movie")
    @RequiredPermission
    public ServerResponse<List<HotMoviesVo>> getHotMovie() {
        List<HotMoviesVo> moviesVoList = hotService.getHotByType(Integer.parseInt(Const.type.MOVIE));
        if(moviesVoList == null){
            return ServerResponse.createByErrorMessage("获取失败，请刷新页面重新尝试");
        } else {
            return ServerResponse.createBySuccess(moviesVoList);
        }
    }

    /**
     * 获取热门电视剧
     *
     * @return ServerResponse<List<HotMoviesVo>>
     */
    @GetMapping("/tv")
    @RequiredPermission
    public ServerResponse<List<HotMoviesVo>> getHotTv() {
        List<HotMoviesVo> moviesVoList = hotService.getHotByType(Integer.parseInt(Const.type.TV));
        if(moviesVoList == null){
            return ServerResponse.createByErrorMessage("获取失败，请刷新页面重新尝试");
        } else {
            return ServerResponse.createBySuccess(moviesVoList);
        }
    }
}
