package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Account;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 14:31
 * @description account mapper
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 通过id查询账户
     *
     * @param accountId 账户id
     * @return Account
     */
    Account selectById(String accountId);
}
