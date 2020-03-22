package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/3/22 21:03
 * @description 资源 vo
 */
@Data
public class ResourceVo {

    /**
     * 资源名称
     */
    @NotEmpty
    private String name;

    /**
     * 集数
     */
    private String episode;

    /**
     * 大小
     */
    private String bigness;

    /**
     * 磁力链接
     */
    @NotEmpty
    private String downloadLink;

    /**
     * 资源对象
     */
    @NotEmpty
    private String object;
}
