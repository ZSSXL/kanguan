package com.kanguan.account;

import com.kanguan.BaseTest;
import com.kanguan.entity.po.Account;
import com.kanguan.mapper.AccountMapper;
import com.kanguan.service.AccountService;
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
    public void xmlTest(){
        String accountId = "test2";
        Account account = accountMapper.selectById(accountId);
        System.out.println(account);
    }

    @Test
    public void selectAllTest(){
        Account account = new Account();
        List<Account> accounts = account.selectAll();
        for (Account a:accounts){
            System.out.println(a);
        }
    }

    @Test
    public void updateTest(){
        Account account = Account.builder()
                .accountId("test")
                .username("小明")
                .password("123456")
                .email("1234567@qq.com")
                .createTime("87837493248")
                .updateTime("9834739123").build();
        int i = accountMapper.updateById(account);
        System.out.println(i);

    }

    @Test
    public void loginTest(){
        String username = "小明";
        String email = "12334534@qq.com";
        String password = "123456";

        Boolean aBoolean = accountService.loginByEmail(email, password);
        System.out.println(aBoolean);

    }


}
