package com.adorablecat.framework.biz.operationlog.aspect;

import java.lang.annotation.*;

/**
 * @author liuRun
 * @date 2025/8/20 17:29
 * @description ApiOperationLog
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     */
    String description() default "";
}
