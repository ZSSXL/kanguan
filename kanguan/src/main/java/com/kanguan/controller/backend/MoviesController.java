package com.kanguan.controller.backend;

import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.config.FtpProperties;
import com.kanguan.entity.po.Movies;
import com.kanguan.entity.vo.MoviesVo;
import com.kanguan.service.FileService;
import com.kanguan.service.MoviesService;
import com.kanguan.util.SessionUtil;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2020/3/18 23:11
 * @description 影视剧控制器
 */
@Slf4j
@RestController
@RequestMapping("/backend/movies")
public class MoviesController {

    private final FileService fileService;
    private final MoviesService moviesService;

    @Autowired
    public MoviesController(FileService fileService, MoviesService moviesService) {
        this.fileService = fileService;
        this.moviesService = moviesService;
    }

    /**
     * 添加影视剧
     *
     * @return ServerResponse<String>
     */
    @PostMapping
    public ServerResponse<String> addMovies(@RequestBody @Valid MoviesVo moviesVo, BindingResult result, HttpSession session) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Boolean existInDb = moviesService.isExistInDb(moviesVo.getName());
            if (existInDb) {
                return ServerResponse.createByErrorMessage("电影/电视剧[ " + moviesVo.getName() + " ]已存在");
            } else {
                try {
                    Movies movies = generateMoviesByVo(moviesVo);
                    Boolean createResult = moviesService.createMovies(movies);
                    if (createResult) {
                        return ServerResponse.createBySuccessMessage("添加成功");
                    } else {
                        return ServerResponse.createByErrorMessage("添加失败");
                    }
                } catch (Exception e) {
                    log.error("add new movies has unknown errors, please check it ...", e);
                    return ServerResponse.createByErrorMessage("添加失败");
                }
            }
        }
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
            return FtpProperties.HTTP_PREFIX + Const.path.COVER_PATH + "/" + targetFileName;
        } else {
            return null;
        }
    }

    /**
     * vo to po
     *
     * @param moviesVo vo
     * @return movies
     */
    private Movies generateMoviesByVo(MoviesVo moviesVo) {
        return Movies.builder()
                .movieId(UUIDUtil.getId())
                .name(moviesVo.getName())
                .cover(Const.DEFAULT_COVER_PATH)
                .director(moviesVo.getDirector())
                .scenarist(moviesVo.getScenarist())
                .staring(moviesVo.getStaring())
                .style(moviesVo.getStyle())
                .type(moviesVo.getType())
                .countryRegion(moviesVo.getCountryRegion())
                .premiere(moviesVo.getPremiere())
                .release(moviesVo.getRelease())
                .length(moviesVo.getLength())
                .anotherName(moviesVo.getAnotherName())
                .score(moviesVo.getScore())
                .introduction(moviesVo.getIntroduction())
                .createTime(TimeUtil.getTimestamp())
                .updateTime(TimeUtil.getTimestamp())
                .build();
    }
}
