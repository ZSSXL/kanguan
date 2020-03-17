package com.kanguan.service;

import com.kanguan.entity.po.Account;

/**
 * @author ZSS
 * @date 2020/3/16 20:46
 * @description account 服务层接口
 */
public interface AccountService {

    /**
     * 通过用户名登录
     *
     * @param username 用户名
     * @param password 密码
     * @return Boolean
     */
    Boolean loginByUsername(String username, String password);

    /**
     * 通过邮箱登录
     *
     * @param email    邮箱
     * @param password 密码
     * @return Boolean
     */
    Boolean loginByEmail(String email, String password);

    /**
     * 创建账户
     *
     * @param account 账户实体
     * @return Boolean
     */
    Boolean createAccount(Account account);
}
