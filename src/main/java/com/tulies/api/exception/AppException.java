package com.tulies.api.exception;

import com.tulies.api.enums.ResultEnum;
import lombok.Getter;

/**
 * @author 王嘉炀
 * @date 2018/6/30 下午4:15
 */
@Getter
public class AppException extends RuntimeException {

    private Integer code;

    public AppException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public AppException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
