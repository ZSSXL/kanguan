package com.kanguan.entity.vo;

import com.kanguan.entity.po.Movies;
import com.kanguan.entity.po.Resource;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/23 22:45
 * @description 获取结果
 */
@Data
@Builder
public class MoviesResourceVo {

    /**
     * 影视剧
     */
    private Movies movies;

    /**
     * 资源
     */
    private List<Resource> resourceList;
}
