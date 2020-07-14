package com.tulies.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 王嘉炀
 * @date 2018/6/30 下午4:16
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(0,"成功"),
    NOT_EXIST(404, "接口不存在"),
    SERVER_ERROR(500,"服务异常"), //请求错误

    MISSING_PARAMS(5000, "参数缺失"),
    PARAM_ERROR(5001, "参数异常"),
    DATA_NOT_EXIT(5002,"数据不存在"),
    DATA_EXIST(5003, "数据已存在"),

    TOKEN_UNAVAILABLE(5100, "TOKEN不可用"),
    USER_NOT_EXIST(5101, "用户不存在"),
    ACCOUNT_PASSWORD_MISMATCH(5102, "账号密码不匹配"),
    ILLEGAL_OPERATION(5103, "非法操作"),

    ;

    private Integer code;
    private String message;

}
