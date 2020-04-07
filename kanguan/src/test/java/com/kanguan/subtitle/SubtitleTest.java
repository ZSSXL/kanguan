package com.kanguan.subtitle;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanguan.BaseTest;
import com.kanguan.entity.vo.SubtitleMoviesVo;
import com.kanguan.mapper.SubtitleMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/4/7 17:44
 * @description subtitle test
 */
public class SubtitleTest extends BaseTest {

    @Autowired
    private SubtitleMapper subtitleMapper;

    @Test
    public void selectSubtitleMainMessageTest() {
        String type = "0";
        IPage<SubtitleMoviesVo> page = new Page<>(1, 30);
        IPage<SubtitleMoviesVo> subtitleMoviesVoIPage = subtitleMapper.selectSubtitleMainMessageByMoviesType(page, type);
        System.out.println("total : " + subtitleMoviesVoIPage.getTotal());
        List<SubtitleMoviesVo> moviesVoList = subtitleMoviesVoIPage.getRecords();
        for (SubtitleMoviesVo vo : moviesVoList) {
            System.out.println(vo);
        }
    }
}
