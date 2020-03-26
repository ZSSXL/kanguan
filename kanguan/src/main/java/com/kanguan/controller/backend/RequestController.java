package com.kanguan.controller.backend;

import com.kanguan.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
