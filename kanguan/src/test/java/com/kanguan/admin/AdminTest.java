package com.kanguan.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.BaseTest;
import com.kanguan.entity.po.Admin;
import com.kanguan.service.AdminService;
import com.kanguan.util.EncryptionUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/19 19:56
 * @description 管理员测试
 */
public class AdminTest extends BaseTest {

    @Autowired
    private AdminService adminService;

    /**
     * 测试是否存在admin
     */
    @Test
    public void isExistInDbTest() {
        Boolean admin = adminService.isExistInDb("admin");
        System.out.println(admin);
    }

    @Test
    public void selectAdmin() {
        Admin admin = new Admin();
        List<Admin> admins = admin.selectAll();
        for (Admin a : admins) {
            System.out.println(a);
        }
    }

    @Test
    public void deleteAdmin() {
        Admin admin = new Admin();
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name", "admin");
        boolean delete = admin.delete(wrapper);
        System.out.println(delete);
    }

    @Test
    public void adminLoginTest() {
        String adminName = "admin";
        String password = "kanguan";
        Admin admin = adminService.loginAdmin(adminName, EncryptionUtil.encrypt(password));
        System.out.println(admin);
    }
}
