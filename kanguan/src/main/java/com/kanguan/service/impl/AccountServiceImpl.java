package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Account;
import com.kanguan.mapper.AccountMapper;
import com.kanguan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2020/3/16 20:47
 * @description account 服务层接口实现
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Boolean loginByUsername(String username, String password) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).eq("password", password);
        Account account = accountMapper.selectOne(wrapper);
        return account != null;
    }

    @Override
    public Boolean loginByEmail(String email, String password) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email).eq("password", password);
        Account account = accountMapper.selectOne(wrapper);
        return account != null;
    }

    @Override
    public Boolean createAccount(Account account) {
        int insert = accountMapper.insert(account);
        return insert == 1;
    }
}
