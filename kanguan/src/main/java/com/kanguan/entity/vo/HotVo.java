package com.kanguan.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ZSS
 * @date 2020/3/25 11:43
 * @description 热门推荐Vo
 */
@Data
public class HotVo {

    /**
     * 热门对象
     */
    @NotEmpty
    private String object;

    /**
     * 类型
     */
    @NotNull
    private Integer type;
}
