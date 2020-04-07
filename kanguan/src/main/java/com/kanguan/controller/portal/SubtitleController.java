package com.kanguan.controller.portal;

import com.kanguan.service.SubtitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZSS
 * @date 2020/4/7 0:11
 * @description 字幕控制器
 */
@Slf4j
@RestController
@RequestMapping("/subtitle")
public class SubtitleController extends BaseController {

    private final SubtitleService subtitleService;

    @Autowired
    public SubtitleController(SubtitleService subtitleService) {
        this.subtitleService = subtitleService;
    }
}
