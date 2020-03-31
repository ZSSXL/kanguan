package com.kanguan.controller.backend;

import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZSS
 * @date 2020/3/26 16:07
 * @description admin 求资源控制器
 */
@RestController("adminRequestController")
@RequestMapping("/backend/request")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * 获取未回复的请求数量
     *
     * @return ServerResponse<Integer>
     */
    @GetMapping
    @AdminExamine
    public ServerResponse<Integer> getUnansweredCount() {
        Integer count = requestService.getUnansweredCount();
        if (count == null) {
            return ServerResponse.createByErrorMessage("查询出错");
        } else {
            return ServerResponse.createBySuccess(count);
        }
    }
}
