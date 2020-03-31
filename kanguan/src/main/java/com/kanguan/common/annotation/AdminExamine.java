package com.kanguan.common.annotation;


import java.lang.annotation.*;


/**
 * @author ZSS
 * @date 2020/3/31 21：15
 * @description 自定义注解 管理员session身份校验
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AdminExamine {
}
