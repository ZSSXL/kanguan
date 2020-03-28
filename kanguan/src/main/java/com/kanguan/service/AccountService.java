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
    Account loginByUsername(String username, String password);

    /**
     * 通过邮箱登录
     *
     * @param email    邮箱
     * @param password 密码
     * @return Boolean
     */
    Account loginByEmail(String email, String password);

    /**
     * 创建账户
     *
     * @param account 账户实体
     * @return Boolean
     */
    Boolean createAccount(Account account);

    /**
     * 查询邮箱是否被使用
     *
     * @param email email
     * @return Boolean
     */
    Boolean emailUsed(String email);

    /**
     * 获取个人信息
     *
     * @param accountId 用户Id
     * @return Account
     */
    Account getUserInfo(String accountId);

    /**
     * 新密码是否和新密码相同
     *
     * @param password  新密码
     * @param accountId 账户Id
     * @return Boolean
     */
    Boolean isSameAsOld(String password, String accountId);

    /**
     * 修改密码
     *
     * @param newPassword 新密码
     * @param accountId   账户Id
     * @return Boolean
     */
    Boolean updatePassword(String newPassword, String accountId);
}
