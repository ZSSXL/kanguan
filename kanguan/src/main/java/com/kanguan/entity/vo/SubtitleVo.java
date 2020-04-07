package com.kanguan.entity.vo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/4/7 19:55
 * @description 字幕 vo
 */
@Data
@Builder
public class SubtitleVo {

    /**
     * 字幕对象
     */
    @NotEmpty
    private String targetId;

    /**
     * 语言
     */
    @NotEmpty
    private String language;

    /**
     * 集数
     */
    private String episode;
}
