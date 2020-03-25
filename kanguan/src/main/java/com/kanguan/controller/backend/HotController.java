package com.kanguan.controller.backend;

import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.entity.po.Hot;
import com.kanguan.entity.vo.HotMoviesVo;
import com.kanguan.entity.vo.HotVo;
import com.kanguan.service.HotService;
import com.kanguan.util.SessionUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/25 11:07
 * @description 热门推荐管理员控制器
 */
@Slf4j
@RestController("adminHotController")
@RequestMapping("/backend/hot")
public class HotController {

    private final HotService hotService;

    @Autowired
    public HotController(HotService hotService) {
        this.hotService = hotService;
    }

    /**
     * 添加热门
     *
     * @param session 用户session
     * @return ServerResponse<String>
     */
    @PostMapping
    public ServerResponse<String> createHot(HttpSession session, @RequestBody @Valid HotVo hotVo, BindingResult result) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Boolean countByType = hotService.checkCountByType(hotVo.getType());
            Boolean existInDb = hotService.isExistInDb(hotVo.getObject());
            if (existInDb) {
                return ServerResponse.createByErrorMessage("已有该推荐");
            } else if (!countByType) {
                return ServerResponse.createByErrorMessage("当前推荐已满，请删除已有推荐再添加");
            } else {
                Hot build = Hot.builder()
                        .hotId(UUIDUtil.getId())
                        .object(hotVo.getObject())
                        .type(hotVo.getType())
                        .build();
                try {
                    Boolean hot = hotService.createHot(build);
                    if (hot) {
                        return ServerResponse.createBySuccess();
                    } else {
                        return ServerResponse.createByError();
                    }
                } catch (Exception e) {
                    log.error("create hot has unknown error, check the log ", e);
                    return ServerResponse.createByErrorMessage("添加热门发生未知异常，请检查日志");
                }
            }
        }
    }

    /**
     * 删除热门
     *
     * @param session 用户session
     * @param hotId   热门Id
     * @return ServerResponse<String>
     */
    @DeleteMapping("/{hotId}")
    public ServerResponse<String> deleteHotById(HttpSession session, @PathVariable("hotId") String hotId) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            Boolean delResult = hotService.deleteHotById(hotId);
            if (delResult) {
                return ServerResponse.createBySuccessMessage("删除成功");
            } else {
                return ServerResponse.createByErrorMessage("删除失败");
            }
        }
    }

    /**
     * 获取热门电影
     *
     * @param session 用户Session
     * @return ServerResponse<Movies>
     */
    @GetMapping("/movie")
    public ServerResponse<List<HotMoviesVo>> getHotMovieByAdmin(HttpSession session) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            List<HotMoviesVo> hotList = hotService.getHotByType(Integer.parseInt(Const.type.MOVIE));
            if (hotList == null) {
                return ServerResponse.createByErrorMessage("没有推荐");
            } else {
                return ServerResponse.createBySuccess(hotList);
            }
        }
    }

    /**
     * 获取热门电视剧
     *
     * @param session 用户Session
     * @return ServerResponse<Movies>
     */
    @GetMapping("/tv")
    public ServerResponse<List<HotMoviesVo>> getHotTvAdmin(HttpSession session) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            List<HotMoviesVo> hotList = hotService.getHotByType(Integer.parseInt(Const.type.TV));
            if (hotList == null) {
                return ServerResponse.createByErrorMessage("没有推荐");
            } else {
                return ServerResponse.createBySuccess(hotList);
            }
        }
    }
}
