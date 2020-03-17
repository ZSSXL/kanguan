package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2020/3/16 16:33
 * @description 管理员 mapper
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
