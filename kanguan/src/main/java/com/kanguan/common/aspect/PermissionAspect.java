package com.kanguan.common.aspect;

import com.kanguan.common.ResponseCode;
import com.kanguan.common.ServerResponse;
import com.kanguan.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZSS
 * @date 2020/3/16 20:22
 * @description 身份校验拦截
 */
@Slf4j
@Aspect
@Component
public class PermissionAspect {

    private final TokenUtil tokenUtil;

    @Autowired
    public PermissionAspect(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Around(value = "@annotation(com.kanguan.common.annotation.RequiredPermission)")
    public Object aroundPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取RequestHeader
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra != null) {
            HttpServletRequest request = sra.getRequest();
            String token = request.getHeader("token");
            if (tokenUtil.isValid(token)) {
                return joinPoint.proceed();
            } else {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "Token已过期，请重新登录");
            }
        } else {
            log.error("权限校验拦截失败");
            return ServerResponse.createByErrorMessage("权限校验拦截失败，请重新发送请求");
        }
    }
}
