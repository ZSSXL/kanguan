package com.kanguan.controller.backend;

import com.kanguan.common.Const;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.config.FtpProperties;
import com.kanguan.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZSS
 * @date 2020/3/18 23:11
 * @description 影视剧控制器
 */
@Slf4j
@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final FileService fileService;

    @Autowired
    public MoviesController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 添加影视剧
     *
     * @param file    文件
     * @param request 请求
     * @return ServerResponse<String>
     */
    public ServerResponse<String> addMovies(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        return null;
    }

    // ------------------------------ 私有工具 ------------------------------- //

    /**
     * 上传封面
     *
     * @param file    file
     * @param request request
     * @return String
     */
    private String uploadCoverImg(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        if (file != null) {
            String targetFileName = fileService.uploadFile(file, path);
            log.info("上传封面成功:" + targetFileName);
            return FtpProperties.HTTP_PREFIX + Const.PATH.COVER_PATH + "/" + targetFileName;
        } else {
            return null;
        }
    }
}
