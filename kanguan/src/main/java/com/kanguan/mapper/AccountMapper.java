package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 14:31
 * @description account mapper
 */
@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {
}
