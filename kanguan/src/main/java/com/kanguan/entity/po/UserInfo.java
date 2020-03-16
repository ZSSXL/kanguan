package com.kanguan.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2020/3/16 15:37
 * @description 用户信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "kg_user_info")
public class UserInfo extends Model<UserInfo> implements Serializable {

    /**
     * 用户信息id
     */
    @TableId
    private String infoId;

    /**
     * 是否会员
     */
    private String member;

    /**
     * 最近登录时间
     */
    private String recentlyLogin;

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
        return infoId;
    }
}
