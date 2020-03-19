package com.kanguan.common.runner;

import com.kanguan.entity.po.Admin;
import com.kanguan.service.AdminService;
import com.kanguan.util.EncryptionUtil;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ZSS
 * @date 2020/3/19 19:46
 * @description 初始化管理员
 */
@Slf4j
@Component
public class InitAdmin implements CommandLineRunner {

    private final AdminService adminService;

    private final static String ADMIN_NAME = "admin";

    private final static String ADMIN_PASSWORD = "kanguan";

    @Autowired
    public InitAdmin(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(String... args) {
        Boolean existInDb = adminService.isExistInDb(ADMIN_NAME);
        if (existInDb) {
            log.info("The initial administrator already exists");
        } else {
            try {
                Boolean admin = adminService.createAdmin(Admin.builder()
                        .adminId(UUIDUtil.getId())
                        .adminName(ADMIN_NAME)
                        .password(EncryptionUtil.encrypt(ADMIN_PASSWORD))
                        .createTime(TimeUtil.getTimestamp())
                        .updateTime(TimeUtil.getTimestamp())
                        .build());
                if (admin) {
                    log.info("initial administrator success ...");
                }
            } catch (Exception e) {
                log.error("init admin error {}", e.getMessage());
            }
        }
    }
}
