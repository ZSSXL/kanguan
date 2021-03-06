package com.kanguan.controller.portal;

import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Account;
import com.kanguan.entity.po.UserInfo;
import com.kanguan.entity.vo.RegisterVo;
import com.kanguan.service.AccountService;
import com.kanguan.service.UserInfoService;
import com.kanguan.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZSS
 * @date 2020/3/17 22:09
 * @description 用户 控制器
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final AccountService accountService;
    private final UserInfoService userInfoService;
    private final TokenUtil tokenUtil;

    @Autowired
    public UserController(AccountService accountService, UserInfoService userInfoService, TokenUtil tokenUtil) {
        this.accountService = accountService;
        this.userInfoService = userInfoService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 注册新用户
     *
     * @param registerVo 注册实体
     * @param result     错误结果
     * @return ServerResponse<String>
     */
    @PostMapping
    public ServerResponse<String> registerUser(@RequestBody @Valid RegisterVo registerVo, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            // 校验验证码是否正确
            String verifyResult = RedisPoolUtil.get(Const.REDIS_PREFIX + registerVo.getEmail());
            if (StringUtils.isEmpty(verifyResult)) {
                return ServerResponse.createByErrorMessage("验证码已过期，请重新发送验证码！");
            } else if (StringUtils.equals(verifyResult, registerVo.getVerifyCode())) {
                // 删除redis缓存
                RedisPoolUtil.del(Const.REDIS_PREFIX + registerVo.getEmail());

                String userId = IdUtil.getId();
                Account account = Account.builder()
                        .accountId(userId)
                        .username(registerVo.getUsername())
                        .email(registerVo.getEmail())
                        .password(EncryptionUtil.encrypt(registerVo.getPassword()))
                        .createTime(TimeUtil.getTimestamp())
                        .updateTime(TimeUtil.getTimestamp()).build();
                UserInfo userInfo = UserInfo.builder()
                        .infoId(userId)
                        .member(Const.member.NO)
                        .createTime(TimeUtil.getTimestamp())
                        .updateTime(TimeUtil.getTimestamp())
                        .build();
                try {
                    accountService.createAccount(account);
                    userInfoService.createUserInfo(userInfo);
                    return ServerResponse.createBySuccess();
                } catch (Exception e) {
                    log.error("create user has unknown error", e);
                    return ServerResponse.createByErrorMessage("创建用户失败！");
                }
            } else {
                return ServerResponse.createByErrorMessage("验证码校验失败, 请重新输入！");
            }
        }
    }

    /**
     * 获取个人信息
     *
     * @param token 用户token
     * @return ServerResponse<Map < String, String>>
     */
    @GetMapping
    @RequiredPermission
    public ServerResponse<Map<String, String>> getUserInfo(@RequestHeader("token") String token) {
        String accountId = tokenUtil.getClaim(token, "userId").asString();
        Account userInfo = accountService.getUserInfo(accountId);
        if (userInfo == null) {
            return ServerResponse.createByErrorMessage("获取个人信息失败");
        } else {
            Map<String, String> map = new HashMap<>(3);
            map.put("email", userInfo.getEmail());
            map.put("username", userInfo.getUsername());
            map.put("accountId", userInfo.getAccountId());
            return ServerResponse.createBySuccess(map);
        }
    }
}
