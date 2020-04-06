package com.kanguan.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @date 2020/4/7 0:21
 * @description 字幕 梗概信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubtitleMoviesVo {

    /**
     * 字幕数量
     */
    private Integer count;

    /**
     * 字幕对象
     */
    private String targetId;

    /**
     * 对象名称
     */
    private String moviesName;
}
