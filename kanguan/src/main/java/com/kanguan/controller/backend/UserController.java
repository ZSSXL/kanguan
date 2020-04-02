package com.kanguan.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.common.Const;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.entity.vo.UserVo;
import com.kanguan.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZSS
 * @date 2020/4/2 22:50
 * @description 用户控制器
 */
@Slf4j
@RestController("adminUserController")
@RequestMapping("/backend/user")
public class UserController {

    private final AccountService accountService;

    @Autowired
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取所有的用户, 区分会员和非会员
     *
     * @return ServerResponse<IPage < Account>>
     */
    @GetMapping("/{member}")
    @AdminExamine
    public ServerResponse<IPage<UserVo>> getAllUser(@PathVariable("member") String member,
                                                    @RequestParam(value = "order", defaultValue = "desc") String order,
                                                    @RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page,
                                                    @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {
        IPage<UserVo> userVoPage = accountService.getUserVo(page, size, member, order);
        if (userVoPage == null) {
            return ServerResponse.createByErrorMessage("查询失败，请刷新重试");
        } else {
            return ServerResponse.createBySuccess(userVoPage);
        }
    }
}
