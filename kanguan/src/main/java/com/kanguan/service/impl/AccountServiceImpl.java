package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kanguan.entity.po.Account;
import com.kanguan.mapper.AccountMapper;
import com.kanguan.service.AccountService;
import org.apache.commons.lang3.StringUtils;
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
    public Account loginByUsername(String username, String password) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).eq("password", password);
        return accountMapper.selectOne(wrapper);
    }

    @Override
    public Account loginByEmail(String email, String password) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email).eq("password", password);
        return accountMapper.selectOne(wrapper);
    }

    @Override
    public Boolean createAccount(Account account) {
        int insert = accountMapper.insert(account);
        return insert == 1;
    }

    @Override
    public Boolean emailUsed(String email) {
        Integer result = accountMapper.selectByEmail(email);
        // 如果为空，return true;
        return result == 1;
    }

    @Override
    public Account getUserInfo(String accountId) {
        return accountMapper.selectById(accountId);
    }
}
