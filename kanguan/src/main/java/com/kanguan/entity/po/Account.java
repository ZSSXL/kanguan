package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 14:21
 * @description 账户实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_account")
public class Account extends Model<Account> implements Serializable {

    /**
     * 账户id
     */
    @TableId
    private String accountId;

    /**
     * 账户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

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
        return accountId;
    }
}
