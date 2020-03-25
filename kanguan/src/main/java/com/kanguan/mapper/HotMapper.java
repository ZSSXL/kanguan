package com.kanguan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanguan.entity.po.Hot;
import com.kanguan.entity.vo.HotMoviesVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/25 10:56
 * @description 热门 mapper
 */
@Mapper
@Repository
public interface HotMapper extends BaseMapper<Hot> {

    /**
     * 通过类型查找热门
     *
     * @param type 类型
     * @return List<HotMoviesVo>
     */
    List<HotMoviesVo> selectHotByType(Integer type);
}
