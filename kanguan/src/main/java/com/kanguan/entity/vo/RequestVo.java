package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ZSS
 * @date 2020/3/25 22:49
 * @description 求资源实体Vo
 */
@Data
public class RequestVo {

    /**
     * 豆瓣地址
     */
    @NotEmpty
    private String doubanAddress;

    /**
     * 资源名
     */
    @NotEmpty
    private String name;
}
