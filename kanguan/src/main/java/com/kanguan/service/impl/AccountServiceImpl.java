package com.kanguan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        return result == 1;
    }

    @Override
    public Account getUserInfo(String accountId) {
        return accountMapper.selectById(accountId);
    }

    @Override
    public Boolean isSameAsOld(String password, String accountId) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", accountId).eq("password", password);
        Account account = accountMapper.selectOne(wrapper);
        return account != null;
    }

    @Override
    public Boolean updatePassword(String newPassword, String accountId) {
        UpdateWrapper<Account> wrapper = new UpdateWrapper<>();
        wrapper.eq("account_id", accountId)
                .set("password", newPassword);
        int update = accountMapper.update(null, wrapper);
        return update == 1;
    }
}
