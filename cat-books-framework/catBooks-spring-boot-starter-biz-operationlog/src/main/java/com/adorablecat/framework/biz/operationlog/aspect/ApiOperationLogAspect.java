package com.adorablecat.framework.biz.operationlog.aspect;

import com.adorablecat.exception.BizException;
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
//        if (annotation == null) {
//            log.info("日志切面没有使用@ApiOperationLog注解！");
//            throw new BizException("<UNK>@ApiOperationLog<UNK>");
//        }
        String description = annotation.description();

        //打印相关参数
        log.info("请求开始：{}，入参{}，请求类{}，请求方法：{}",
                description, collect, simpleName, methodName);
//执行请求方法
        Object proceedResult = joinPoint.proceed();
        //执行时间
        long costTime = System.currentTimeMillis() - startTime;

        //出参
        log.info("请求结束{},耗时{}，出参：{}", description, costTime, JsonUtils.toJsonString(proceedResult));
        return proceedResult;
    }
}
