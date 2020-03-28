package com.kanguan.account;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kanguan.BaseTest;
import com.kanguan.entity.po.Account;
import com.kanguan.mapper.AccountMapper;
import com.kanguan.service.AccountService;
import com.kanguan.util.EncryptionUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/16 15:22
 * @description 账户测试
 */
public class AccountTest extends BaseTest {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    @Test
    public void xmlTest() {
        String accountId = "test2";
        Account account = accountMapper.selectById(accountId);
        System.out.println(account);
    }

    @Test
    public void selectAllTest() {
        Account account = new Account();
        List<Account> accounts = account.selectAll();
        for (Account a : accounts) {
            System.out.println(a);
        }
    }

    @Test
    public void updateTest() {
        UpdateWrapper<Account> wrapper = new UpdateWrapper<>();
        wrapper.eq("account_id", "2c211cabe58f4d349b8dcfc11d343a9a")
                .set("password", EncryptionUtil.encrypt("654321"));
        int update = accountMapper.update(null, wrapper);
        System.out.println(update);
    }


    @Test
    public void loginTest() {
        String email = "12334534@qq.com";
        String password = "123456";

        Account account = accountService.loginByEmail(email, password);
        System.out.println(account);

    }


}
