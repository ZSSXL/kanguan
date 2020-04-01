package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/4/1 20:43
 * @description 请求响应Vo
 */
@Data
public class ResponseVo {

    /**
     * 请求Id
     */
    @NotEmpty
    private String requestId;

    /**
     * 影视剧名称
     */
    @NotEmpty
    private String moviesName;
}
