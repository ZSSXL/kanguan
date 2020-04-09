package com.kanguan.controller.portal;

import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Subtitle;
import com.kanguan.service.SubtitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 获取字幕资源
     *
     * @param targetId 字幕对象
     * @return List<Subtitle>
     */
    @GetMapping("/{targetId}")
    @RequiredPermission
    public ServerResponse<List<Subtitle>> getSubtitleByTargetId(@PathVariable("targetId") String targetId) {
        List<Subtitle> subtitleList = subtitleService.getSubtitleByTargetId(targetId);
        if (subtitleList == null) {
            return ServerResponse.createByErrorMessage("资源未找到，请刷新重试");
        } else {
            return ServerResponse.createBySuccess(subtitleList);
        }
    }
}
