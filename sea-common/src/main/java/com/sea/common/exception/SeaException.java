package com.sea.common.exception;

import com.sea.common.enums.ExceptionEnum;
import lombok.Getter;
/*
 *自定义异常类
 */
@Getter
public class SeaException extends RuntimeException{
    private ExceptionEnum exceptionEnum;

    public SeaException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
