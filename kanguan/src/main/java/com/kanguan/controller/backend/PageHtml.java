package com.kanguan.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ZSS
 * @date 2020/3/19 22:16
 * @description 页面跳转控制器
 */
@Controller
public class PageHtml {

    /**
     * 页面跳转
     *
     * @param page page
     * @return String
     */
    @GetMapping("/{page}")
    public String helloHtml(@PathVariable String page) {
        return page;
    }
}
