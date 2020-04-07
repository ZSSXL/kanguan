package com.kanguan.controller.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanguan.common.Const;
import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.AdminExamine;
import com.kanguan.common.config.FtpProperties;
import com.kanguan.controller.portal.BaseController;
import com.kanguan.entity.po.Subtitle;
import com.kanguan.entity.vo.SubtitleMoviesVo;
import com.kanguan.entity.vo.SubtitleVo;
import com.kanguan.service.FileService;
import com.kanguan.service.SubtitleService;
import com.kanguan.util.TimeUtil;
import com.kanguan.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2020/4/7 0:11
 * @description 字幕控制器
 */
@Slf4j
@RestController("adminSubtitleController")
@RequestMapping("/backend/subtitle")
public class SubtitleController extends BaseController {

    private final SubtitleService subtitleService;
    private final FileService fileService;

    @Autowired
    public SubtitleController(SubtitleService subtitleService, FileService fileService) {
        this.subtitleService = subtitleService;
        this.fileService = fileService;
    }


    /**
     * 获取
     *
     * @param type 类型
     * @param page 当前页
     * @param size 每页大小
     * @return IPage<SubtitleMoviesVo>
     */
    @GetMapping("/{type}")
    @AdminExamine
    public ServerResponse<IPage<SubtitleMoviesVo>> getSubtitleMainMessage(@PathVariable("type") String type,
                                                                          @RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page,
                                                                          @RequestParam(value = "size", defaultValue = Const.DEFAULT_PAGE_SIZE) Integer size) {

        IPage<SubtitleMoviesVo> smVoPage = subtitleService.getSubtitleMainMessage(type, new Page<>(page, size));
        if (smVoPage == null) {
            return ServerResponse.createByErrorMessage("获取数据失败，请刷新重试");
        } else {
            return ServerResponse.createBySuccess(smVoPage);
        }
    }

    /**
     * 添加字幕
     *
     * @return String
     */
    @PostMapping
    @AdminExamine
    public ServerResponse<String> addSubtitleToTarget(@Valid SubtitleVo subtitleVo
            , @RequestParam(value = "subtitle", required = false) MultipartFile subtitle
            , BindingResult result
            , HttpServletRequest request) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            String originalFilename = subtitle.getOriginalFilename();
            if (originalFilename == null) {
                return ServerResponse.createByErrorMessage("获取文件名失败...");
            } else {
                // 获取扩展名
                String fileExtensionName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                String uploadSubtitle = uploadSubtitle(subtitle, request);
                if (uploadSubtitle == null) {
                    return ServerResponse.createByErrorMessage("上传文件出错...");
                } else {
                    Subtitle build = Subtitle.builder()
                            .subtitleId(UUIDUtil.getId())
                            .name(originalFilename)
                            .format(fileExtensionName)
                            .download(uploadSubtitle)
                            .language(subtitleVo.getLanguage())
                            .targetId(subtitleVo.getTargetId())
                            .episode(subtitleVo.getEpisode())
                            .createTime(TimeUtil.getTimestamp())
                            .updateTime(TimeUtil.getTimestamp())
                            .build();
                    Boolean insertResult = subtitleService.addSubtitleToTarget(build);
                    if (insertResult) {
                        return ServerResponse.createBySuccessMessage("添加成功");
                    } else {
                        return ServerResponse.createByErrorMessage("添加失败");
                    }
                }
            }
        }
    }

    /**
     * 删除字幕
     *
     * @param subtitleId 字幕Id
     * @return String
     */
    @DeleteMapping("/{subtitleId}")
    @AdminExamine
    public ServerResponse<String> deleteSubtitleById(@PathVariable("subtitleId") String subtitleId) {
        Boolean result = subtitleService.deleteSubtitleById(subtitleId);
        if (result) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByError();
        }
    }

    // ------------------------------ 内部私有工具 ------------------------------- //

    /**
     * 上传字幕
     *
     * @param file    file
     * @param request request
     * @return String
     */
    private String uploadSubtitle(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        if (file != null) {
            String targetFileName = fileService.uploadFile(file, Const.path.SUBTITLE_PATH, path);
            log.info("上传字幕成功: " + targetFileName);
            return FtpProperties.HTTP_PREFIX + Const.path.SUBTITLE_PATH + "/" + targetFileName;
        } else {
            return null;
        }
    }
}
