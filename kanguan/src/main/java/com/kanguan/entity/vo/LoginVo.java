package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/3/16 20:48
 * @description 登录实体
 */
@Data
public class LoginVo {

    /**
     * 登录账户 用户名/邮箱
     */
    @NotEmpty
    private String account;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

}
