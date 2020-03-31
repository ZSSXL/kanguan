package com.kanguan.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.common.config.FtpProperties;
import com.kanguan.entity.po.Movies;
import com.kanguan.entity.vo.MoviesVo;
import com.kanguan.service.FileService;
import com.kanguan.service.MoviesService;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2020/3/18 23:11
 * @description 影视剧控制器
 */
@Slf4j
@RestController("adminMoviesController")
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
     * @param moviesVo 影视剧实体Vo
     * @param cover    封面
     * @param result   错误结果
     * @param request  request
     * @return ServerResponse
     */
    @PostMapping
    @AdminExamine
    public ServerResponse<String> addMovies(@Valid MoviesVo moviesVo
            , @RequestParam(value = "cover", required = false) MultipartFile cover
            , BindingResult result
            , HttpServletRequest request) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Boolean existInDb = moviesService.isExistInDb(moviesVo.getName());
            if (existInDb) {
                return ServerResponse.createByErrorMessage("电影/电视剧[ " + moviesVo.getName() + " ]已存在");
            } else {
                String coverUrl = uploadCoverImg(cover, request);
                if (StringUtils.isEmpty(coverUrl)) {
                    return ServerResponse.createByErrorMessage("上传封面失败 ...");
                } else {
                    try {
                        Movies movies = generateMoviesByVo(moviesVo);
                        movies.setCover(coverUrl);
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
    }

    /**
     * 获取所有的电影资源
     *
     * @param page 第几页
     * @param size 每页大小
     * @return ServerResponse<IPage < Movies>>
     */
    @GetMapping("/movie")
    @AdminExamine
    public ServerResponse<IPage<Movies>> getAllMovie(@RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page
            , @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {
        IPage<Movies> allMovies = moviesService.getAllMovies(Const.type.MOVIE, page, size);
        return ServerResponse.createBySuccess(allMovies);
    }

    /**
     * 获取所有的电视剧资源
     *
     * @param page 第几页
     * @param size 每页大小
     * @return ServerResponse<IPage < Movies>>
     */
    @GetMapping("/tv")
    @AdminExamine
    public ServerResponse<IPage<Movies>> getAllTv(@RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page
            , @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {
        IPage<Movies> allMovies = moviesService.getAllMovies(Const.type.TV, page, size);
        return ServerResponse.createBySuccess(allMovies);
    }

    /**
     * 删除影视剧
     *
     * @param movieId 影视剧Id
     * @return ServerResponse<String>
     */
    @DeleteMapping("/{movieId}")
    @AdminExamine
    public ServerResponse<String> deleteMoviesById(@PathVariable(value = "movieId") String movieId) {
        if (StringUtils.isEmpty(movieId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Boolean result = moviesService.deleteMoviesById(movieId);
            if (result) {
                return ServerResponse.createBySuccess();
            } else {
                return ServerResponse.createByError();
            }
        }
    }

    // ------------------------------ 内部私有工具 ------------------------------- //

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
            String targetFileName = fileService.uploadFile(file, Const.path.COVER_PATH, path);
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
