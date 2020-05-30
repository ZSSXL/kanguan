package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Account;
import com.kanguan.entity.vo.LoginVo;
import com.kanguan.service.AccountService;
import com.kanguan.util.EmailUtil;
import com.kanguan.util.EncryptionUtil;
import com.kanguan.util.MapUtil;
import com.kanguan.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2020/3/16 20:45
 * @description 账户 控制器
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private final AccountService accountService;
    private final TokenUtil tokenUtil;

    @Autowired
    public AccountController(AccountService accountService, TokenUtil tokenUtil) {
        this.accountService = accountService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 用户登录
     *
     * @param loginVo 登录实体
     * @return ServerResponse<String>
     */
    @PostMapping
    public ServerResponse<String> login(@RequestBody @Valid LoginVo loginVo, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            // 判断输入的account是用户名还是email
            String token;
            if (EmailUtil.isEmail(loginVo.getAccount())) {
                Account emailResult = accountService.loginByEmail(loginVo.getAccount(), EncryptionUtil.encrypt(loginVo.getPassword()));
                if (emailResult != null) {
                    // 完成token
                    token = tokenUtil.createJwt(MapUtil.create(
                            "userId", emailResult.getAccountId(),
                            "username", emailResult.getUsername(),
                            "email", emailResult.getEmail()));
                    return ServerResponse.createBySuccess("登录成功", token);
                } else {
                    return ServerResponse.createByErrorMessage("登录失败，邮箱或者密码错误");
                }
            } else {
                Account usernameResult = accountService.loginByUsername(loginVo.getAccount(), EncryptionUtil.encrypt(loginVo.getPassword()));
                if (usernameResult != null) {
                    token = tokenUtil.createJwt(MapUtil.create(
                            "userId", usernameResult.getAccountId(),
                            "username", usernameResult.getUsername(),
                            "email", usernameResult.getEmail()));
                    return ServerResponse.createBySuccess("登录成功", token);
                } else {
                    return ServerResponse.createByErrorMessage("登录失败，邮箱或者密码错误");
                }
            }
        }
    }

    /**
     * 修改密码
     *
     * @param token       用户token
     * @param newPassword 新密码
     * @return ServerResponse<String>
     */
    @PutMapping
    @RequiredPermission
    public ServerResponse<String> modifyPassword(@RequestHeader("token") String token, @RequestBody String newPassword) {
        if (StringUtils.isEmpty(newPassword)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            String accountId = tokenUtil.getClaim(token, "userId").asString();
            Boolean sameAsOld = accountService.isSameAsOld(EncryptionUtil.encrypt(newPassword), accountId);
            if (sameAsOld) {
                return ServerResponse.createByErrorMessage("新密码与旧密码相同");
            } else {
                Boolean updateResult = accountService.updatePassword(EncryptionUtil.encrypt(newPassword), accountId);
                if (updateResult) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                } else {
                    return ServerResponse.createByErrorMessage("修改失败");
                }
            }
        }
    }
}
