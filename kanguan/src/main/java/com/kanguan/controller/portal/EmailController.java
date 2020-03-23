package com.kanguan.controller.portal;

import com.kanguan.common.Const;
import com.kanguan.common.ServerResponse;
import com.kanguan.service.AccountService;
import com.kanguan.util.EmailUtil;
import com.kanguan.util.IdUtil;
import com.kanguan.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/3/17 22:18
 * @description 邮箱控制器
 */
@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController extends BaseController {

    private final AccountService accountService;

    @Autowired
    public EmailController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 邮箱发送校验码
     *
     * @param email 邮箱
     * @return ServerResponse
     */
    @PostMapping
    public ServerResponse<String> sendEmail(@RequestBody @NotEmpty String email, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorMessage("邮箱不能为空");
        } else if (EmailUtil.isEmail(email)) {
            Boolean used = accountService.emailUsed(email);
            if (used) {
                return ServerResponse.createByErrorMessage("该邮箱以注册，请更换一个邮箱使用！");
            } else {
                String verifyCode = IdUtil.generateVerifyCode();
                RedisPoolUtil.setAndExpire(Const.REDIS_PREFIX + email, verifyCode);
                try {
                    EmailUtil.sendVerifyCode(email, verifyCode);
                    return ServerResponse.createBySuccess();
                } catch (MessagingException e) {
                    log.error("[{}] 邮箱发送验证码失败，发生未知异常 ", email, e);
                    return ServerResponse.createByError();
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("请输入正确的邮箱");
        }
    }
}
