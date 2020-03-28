package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Request;
import com.kanguan.entity.vo.RequestVo;
import com.kanguan.service.RequestService;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.TokenUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/25 22:50
 * @description 求资源控制器
 */
@Slf4j
@RestController
@RequestMapping("/request")
public class RequestController extends BaseController {

    private final RequestService requestService;
    private final TokenUtil tokenUtil;

    @Autowired
    public RequestController(RequestService requestService, TokenUtil tokenUtil) {
        this.requestService = requestService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 添加资源请求
     *
     * @param requestVo 请求资源Vo
     * @param result    错误结果
     * @return ServerResponse<String>
     */
    @PostMapping
    @RequiredPermission
    public ServerResponse<String> createRequest(@RequestBody @Valid RequestVo requestVo
            , @RequestHeader("token") String token, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            String requester = tokenUtil.getClaim(token, "userId").asString();
            Boolean existInDb = requestService.isExistInDb(requester, requestVo.getName());
            if (existInDb) {
                return ServerResponse.createByErrorMessage("已经申请过了，请到个人中心查看");
            } else {
                Request build = Request.builder()
                        .requestId(UUIDUtil.getId())
                        .requester(requester)
                        .name(requestVo.getName())
                        .exist("0")
                        .doubanAddress(requestVo.getDoubanAddress())
                        .createTime(TimeUtil.getTimestamp())
                        .updateTime(TimeUtil.getTimestamp())
                        .build();
                try {
                    Boolean requestResult = requestService.createRequest(build);
                    if (requestResult) {
                        return ServerResponse.createBySuccessMessage("请求成功");
                    } else {
                        return ServerResponse.createByErrorMessage("请求失败");
                    }
                } catch (Exception e) {
                    log.error("create request has unknown error, please check the log ", e);
                    return ServerResponse.createByErrorMessage("发生未知异常, 反馈给站主");
                }
            }
        }
    }

    /**
     * 获取资源请求信息
     *
     * @param token 用户token
     * @return ServerResponse<List < Request>>
     */
    @GetMapping
    @RequiredPermission
    public ServerResponse<List<Request>> getRequest(@RequestHeader("token") String token) {
        String requester = tokenUtil.getClaim(token, "userId").asString();
        List<Request> requestByRequester = requestService.getRequestByRequester(requester);
        if (requestByRequester == null) {
            return ServerResponse.createByErrorMessage("查询失败，请更新重试...");
        } else {
            return ServerResponse.createBySuccess(requestByRequester);
        }
    }

    /**
     * 删除资源请求
     *
     * @param token 用户token
     * @return ServerResponse<List < Request>>
     */
    @DeleteMapping("/{requestId}")
    @RequiredPermission
    public ServerResponse<String> getRequest(@RequestHeader("token") String token, @PathVariable("requestId") String requestId) {
        String requester = tokenUtil.getClaim(token, "userId").asString();
        Boolean result = requestService.deleteRequest(requester, requestId);
        if (result) {
            return ServerResponse.createBySuccessMessage("删除成功");
        } else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }
}
