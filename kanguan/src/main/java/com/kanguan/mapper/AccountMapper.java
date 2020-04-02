package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.entity.po.Account;
import com.kanguan.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 14:31
 * @description account mapper
 */
@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 通过邮箱查询邮箱
     *
     * @param email 邮箱
     * @return String
     */
    Integer selectByEmail(String email);

    /**
     * 分页多条件多表查询用户
     *
     * @param page   分页详情
     * @param member 会员
     * @param order  排序
     * @return IPage<UserVo>
     */
    IPage<UserVo> selectUserVoByMemberAndOrder(IPage<UserVo> page, @Param("member") String member, @Param("order") String order);

}
