package com.adorablecat.framework.biz.operationlog.aspect;

import com.adorablecat.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author liuRun
 * @date 2025/8/20 17:31
 * @description ApiOperationLogAspect
 */
@Aspect
@Slf4j
public class ApiOperationLogAspect {
    @Pointcut("@annotation(com.adorablecat.framework.biz.operationlog.aspect.ApiOperationLog)")
    public void apiOperationLog() {
    }

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //请求类名
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        //请求方法名
        String methodName = joinPoint.getSignature().getName();

        //实际的入参
        Object[] args = joinPoint.getArgs();

        //入参转JSON字符串
        String collect = Arrays.stream(args).map(JsonUtils::toJsonString).collect(Collectors.joining(","));

        //获取注解描述信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ApiOperationLog annotation = signature.getMethod().getAnnotation(ApiOperationLog.class);
        if (annotation != null) {
            log.info();
        }
        String description = annotation.description();
        return joinPoint.proceed();
    }
}
