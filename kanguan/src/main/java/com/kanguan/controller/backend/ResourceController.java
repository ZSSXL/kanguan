package com.kanguan.controller.backend;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.entity.po.Resource;
import com.kanguan.entity.vo.ResourceVo;
import com.kanguan.service.ResourceService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2020/3/22 20:57
 * @description 资源管理
 */
@Slf4j
@RestController
@RequestMapping("/backend/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ServerResponse<String> addResource(HttpSession session, @RequestBody @Valid ResourceVo resourceVo, BindingResult result) {
        if (SessionUtil.checkSession(session)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Boolean existInDbByName = resourceService.isExistInDbByName(resourceVo.getName());
            if (existInDbByName) {
                return ServerResponse.createByErrorMessage("该资源已存在...");
            } else {
                String format = resourceVo.getName().substring(resourceVo.getName().lastIndexOf(".") + 1);
                try {
                    Resource build = Resource.builder()
                            .resourceId(UUIDUtil.getId())
                            .name(resourceVo.getName())
                            .bigness(resourceVo.getBigness())
                            .downloadLink(resourceVo.getDownloadLink())
                            .episode(resourceVo.getEpisode())
                            .format(format)
                            .object(resourceVo.getObject())
                            .createTime(TimeUtil.getTimestamp())
                            .updateTime(TimeUtil.getTimestamp())
                            .build();
                    Boolean addResult = resourceService.addResource(build);
                    if (addResult) {
                        return ServerResponse.createBySuccess();
                    } else {
                        return ServerResponse.createByError();
                    }
                } catch (Exception e) {
                    log.error("add resource has unknown error " + e);
                    return ServerResponse.createByErrorMessage("添加资源失败,发生未知异常...");
                }
            }
        }
    }
}
