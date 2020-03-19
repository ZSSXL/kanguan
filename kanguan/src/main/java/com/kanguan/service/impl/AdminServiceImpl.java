package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Admin;
import com.kanguan.mapper.AdminMapper;
import com.kanguan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2020/3/19 19:52
 * @description 管理员服务层接口方法实现
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Boolean isExistInDb(String adminName) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name", adminName);
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin != null;
    }

    @Override
    public Boolean createAdmin(Admin admin) {
        return admin.insert();
    }

    @Override
    public Admin loginAdmin(String adminName, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name", adminName)
                .eq("password", password);
        return adminMapper.selectOne(queryWrapper);
    }
}
