package com.adorablecat.exception;

import lombok.Data;

/**
 * @author liuRun
 * @date 2025/8/20 16:09
 * @description BizException
 */
@Data
public class BizException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
