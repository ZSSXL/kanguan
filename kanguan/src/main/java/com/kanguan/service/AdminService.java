package com.kanguan.service;

import com.kanguan.entity.po.Admin;

/**
 * @author ZSS
 * @date 2020/3/19 19:52
 * @description 管理员服务层接口
 */
public interface AdminService {

    /**
     * 是否存在管理员admin
     *
     * @param adminName 管理员名称
     * @return Boolean
     */
    Boolean isExistInDb(String adminName);

    /**
     * 创建管理员
     *
     * @param admin 管理员实体
     * @return Admin
     */
    Boolean createAdmin(Admin admin);

    /**
     * 管理员登录
     *
     * @param adminName 管理员
     * @param password  密码
     * @return Admin
     */
    Admin loginAdmin(String adminName, String password);
}
