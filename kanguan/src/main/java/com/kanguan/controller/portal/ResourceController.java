package com.kanguan.controller.portal;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.common.annotation.RequiredPermission;
import com.kanguan.entity.po.Resource;
import com.kanguan.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZSS
 * @date 2020/3/24 22:07
 * @description 用户获取资源
 */
@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 通过Id获取资源
     *
     * @param resourceId 资源Id
     * @return ServerResponse<Resource>
     */
    @GetMapping("/{resourceId}")
    @RequiredPermission
    public ServerResponse<Resource> getResourceById(@PathVariable("resourceId") String resourceId) {
        if (StringUtils.isEmpty(resourceId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getDesc());
        } else {
            Resource resource = resourceService.getResourceById(resourceId);
            if (resource == null) {
                return ServerResponse.createByErrorMessage("资源获取失败，请重新尝试....");
            } else {
                return ServerResponse.createBySuccess(resource);
            }
        }
    }
}
