package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 15:38
 * @description 资源申请表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_request")
public class Request extends Model<Request> implements Serializable {

    /**
     * 请求Id
     */
    @TableId
    private String requestId;

    /**
     * 请求者
     */
    private String requester;

    /**
     * 豆瓣地址
     */
    private String doubanAddress;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 是否存在：不存在为0，未收录; 存在则将地址放在这里
     */
    private String exist;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    @Override
    protected Serializable pkVal() {
        return requestId;
    }
}
