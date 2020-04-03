package com.kanguan.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.entity.vo.UserVo;
import com.kanguan.service.AccountService;
import com.kanguan.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    private final UserInfoService userInfoService;

    @Autowired
    public UserController(AccountService accountService, UserInfoService userInfoService) {
        this.accountService = accountService;
        this.userInfoService = userInfoService;
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

    /**
     * 获取已经总用户和会员用户的数量
     *
     * @return Integer
     */
    @GetMapping("/count")
    @AdminExamine
    public ServerResponse<Map<String, Integer>> getUserCount() {
        Integer totalCount = accountService.getRegisterUserCount();
        Integer memberCount = userInfoService.getMemberCount();
        Map<String, Integer> map = new HashMap<>(2);
        if (totalCount == null || memberCount == null) {
            return ServerResponse.createByErrorMessage("查询失败，请刷新重试");
        } else {
            map.put("total", totalCount);
            map.put("member", memberCount);
            return ServerResponse.createBySuccess(map);
        }
    }

    /**
     * 删除用户
     *
     * @param userId 用户Id
     * @return ServerResponse<String>s
     */
    @DeleteMapping("/{userId}")
    @AdminExamine
    public ServerResponse<String> deleteUserById(@PathVariable("userId") String userId) {
        if (StringUtils.isEmpty(userId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            return ServerResponse.createBySuccessMessage("删除功能之后再做");
        }
    }
}
