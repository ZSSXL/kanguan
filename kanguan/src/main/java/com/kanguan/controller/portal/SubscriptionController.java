package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Subscription;
import com.kanguan.entity.vo.SubResourceVo;
import com.kanguan.service.SubscriptionService;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.TokenUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/26 22:43
 * @description 订阅
 */
@Slf4j
@RestController
@RequestMapping("/subscription")
public class SubscriptionController extends BaseController {

    private final SubscriptionService subscriptionService;
    private final TokenUtil tokenUtil;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, TokenUtil tokenUtil) {
        this.subscriptionService = subscriptionService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 添加订阅
     *
     * @param subObject 订阅对象
     * @param token     用户token
     * @return ServerResponse<String>
     */
    @PostMapping
    @RequiredPermission
    public ServerResponse<String> addSubscription(@RequestBody String subObject, @RequestHeader("token") String token) {
        if (StringUtils.isEmpty(subObject)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            String subscriber = tokenUtil.getClaim(token, "userId").asString();
            Boolean existInDb = subscriptionService.isExistInDb(subObject, subscriber);
            if (existInDb) {
                return ServerResponse.createByErrorMessage("已经订阅了,去个人中心查看");
            } else {
                Subscription build = Subscription.builder()
                        .subId(UUIDUtil.getId())
                        .subscriber(subscriber)
                        .subObject(subObject)
                        .createTime(TimeUtil.getTimestamp())
                        .updateTime(TimeUtil.getTimestamp())
                        .build();
                try {
                    Boolean result = subscriptionService.addSubscription(build);
                    if (result) {
                        return ServerResponse.createBySuccess();
                    } else {
                        return ServerResponse.createByErrorMessage("订阅失败，请重新尝试");
                    }
                } catch (Exception e) {
                    log.error("add subscription has unknown error ", e);
                    return ServerResponse.createByErrorMessage("添加订阅发声未知有异常");
                }
            }
        }
    }

    /**
     * 获取个人订阅信息
     *
     * @param token 用户token
     * @return
     */
    @GetMapping
    @RequiredPermission
    public ServerResponse<List<SubResourceVo>> getSubscription(@RequestHeader("token") String token) {
        String subscriber = tokenUtil.getClaim(token, "userId").asString();
        List<SubResourceVo> subBySubscriber = subscriptionService.getSubBySubscriber(subscriber);
        if (subBySubscriber == null) {
            return ServerResponse.createByErrorMessage("获取失败，请刷新页面重新尝试");
        } else {
            return ServerResponse.createBySuccess(subBySubscriber);
        }
    }

    /**
     * 删除个人订阅
     *
     * @param subId 订阅Id
     * @param token 用户token
     * @return ServerResponse<String>
     */
    @DeleteMapping("/{subId}")
    @RequiredPermission
    public ServerResponse<String> deleteSubscriptionById(@PathVariable("subId") String subId, @RequestHeader("token") String token) {
        if (StringUtils.isEmpty(subId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            String subscriber = tokenUtil.getClaim(token, "userId").asString();
            Boolean result = subscriptionService.deleteSubById(subId, subscriber);
            if (result) {
                return ServerResponse.createBySuccessMessage("删除成功");
            } else {
                return ServerResponse.createByErrorMessage("删除失败");
            }
        }
    }

}
