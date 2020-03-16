package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.entity.vo.LoginVo;
import com.kanguan.service.AccountService;
import com.kanguan.util.EncryptionUtil;
import com.kanguan.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2020/3/16 20:45
 * @description 账户 控制器
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final TokenUtil tokenUtil;

    @Autowired
    public AccountController(AccountService accountService, TokenUtil tokenUtil) {
        this.accountService = accountService;
        this.tokenUtil = tokenUtil;
    }

    /**
     *用户登录
     * @param loginVo 登录实体
     * @return ServerResponse<String>
     */
    @PostMapping
    public ServerResponse<String> login(@RequestBody @Valid LoginVo loginVo, BindingResult result) {
        if(result.hasErrors()){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            // 判断输入的account是用户名还是email
            if(isEmail(loginVo.getAccount())){
                Boolean emailResult = accountService.loginByEmail(loginVo.getAccount(), EncryptionUtil.encrypt(loginVo.getPassword()));
                if(emailResult){
                    // 完成token
                    return ServerResponse.createBySuccessMessage("登录成功");
                } else {
                    return ServerResponse.createByErrorMessage("登录失败，邮箱或者密码错误");
                }
            } else {
                Boolean usernameResult = accountService.loginByUsername(loginVo.getAccount(), EncryptionUtil.encrypt(loginVo.getPassword()));
                if(usernameResult){
                    return ServerResponse.createBySuccessMessage("登录成功");
                } else {
                    return ServerResponse.createByErrorMessage("登录失败，邮箱或者密码错误");
                }
            }
        }
    }


    // =================================== 内部私有方法 =============================== //

    /**
     * 判断字符串是否为邮箱
     *
     * @param str 字符串
     * @return Boolean
     */
    private static Boolean isEmail(String str) {
        boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }
}
