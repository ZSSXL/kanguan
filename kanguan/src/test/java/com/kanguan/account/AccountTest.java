package com.kanguan.account;

import com.kanguan.BaseTest;
import com.kanguan.entity.po.Account;
import com.kanguan.mapper.AccountMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZSS
 * @date 2020/3/16 15:22
 * @description 账户测试
 */
public class AccountTest extends BaseTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void xmlTest(){
        String accountId = "test2";
        Account account = accountMapper.selectById(accountId);
        System.out.println(account);
    }

}
