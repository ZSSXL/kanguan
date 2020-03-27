package com.kanguan.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/27 10:27
 * @description 订阅 资源 实体Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubResourceVo implements Serializable {

    /**
     * 订阅Id
     */
    private String subId;

    /**
     * 订阅对象
     */
    private String subObject;

    /**
     * 订阅名称
     */
    private String name;

    /**
     * 最近更新时间
     */
    private String updateTime;
}
