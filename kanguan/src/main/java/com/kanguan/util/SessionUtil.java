package com.kanguan.util;

import com.kanguan.common.Const;
import com.kanguan.entity.po.Admin;

import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @date 2020/3/19 21:15
 * @description httpSession 工具
 */
public class SessionUtil {

    /**
     * 检查session是否为空
     *
     * @param session 管理员session
     * @return Boolean
     */
    public static Boolean checkSession(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        return admin == null;
    }
}
