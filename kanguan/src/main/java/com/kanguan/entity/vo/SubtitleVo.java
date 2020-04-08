package com.kanguan.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/4/7 19:55
 * @description 字幕 vo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
