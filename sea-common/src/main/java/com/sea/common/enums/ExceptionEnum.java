package com.sea.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    USER_NOT_EXIST(404, "用户不存在"),
    USERNAME_OR_PASSWORD_ERROR(400, "账号或密码错误"),
    ;
    int value;
    String message;

    public int value() {
        return this.value;
    }

    public String message() {
        return this.message;
    }
}
