package com.kanguan.controller.backend;

import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.entity.po.Admin;
import com.kanguan.entity.vo.LoginVo;
import com.kanguan.service.AdminService;
import com.kanguan.util.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2020/3/19 19:45
 * @description 管理员控制器
 */
@Slf4j
@RestController
@RequestMapping("/backend/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理员登录
     *
     * @param loginVo 登录实体
     * @param result  错误结果
     * @param session 用户session
     * @return ServerResponse<String>
     */
    @PostMapping
    public ServerResponse<String> adminLogin(@RequestBody @Valid LoginVo loginVo, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Admin admin = adminService.loginAdmin(loginVo.getAccount(), EncryptionUtil.encrypt(loginVo.getPassword()));
            if (admin == null) {
                return ServerResponse.createByErrorMessage("登录失败！[账号或密码错误] ...");
            } else {
                session.setAttribute(Const.CURRENT_ADMIN, admin);
                return ServerResponse.createBySuccessMessage("登录成功");
            }
        }
    }

    /**
     * 退出登录
     *
     * @return ServerResponse
     */
    @PostMapping("/logout")
    @AdminExamine
    public ServerResponse<String> adminLogout() {
        return ServerResponse.createBySuccess();
    }
}
