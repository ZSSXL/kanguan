package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/3/17 22:15
 * @description 注册实体vo
 */
@Data
public class RegisterVo {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String verifyCode;
}
