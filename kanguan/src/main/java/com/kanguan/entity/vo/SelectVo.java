package com.kanguan.entity.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author ZSS
 * @date 2020/3/30 0:18
 * @description 检索条件Vo
 */
@Data
@Builder
public class SelectVo {

    private String selectType;

    private String selectStyle;

    private String selectRegion;

    private String selectPremiere;
}
