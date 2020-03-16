package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 15:37
 * @description 管理员表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_admin")
public class Admin extends Model<Admin> implements Serializable {

    /**
     * 管理员Id
     */
    @TableId
    private String adminId;

    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 管理员密码
     */
    private String password;

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
        return adminId;
    }
}
