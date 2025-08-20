package com.adorablecat.exception;

/**
 * @author liuRun
 * @date 2025/8/20 16:03
 * @description BaseExceptionInterface
 */
public interface BaseExceptionInterface {
    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
