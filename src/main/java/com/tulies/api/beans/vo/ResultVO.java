package com.tulies.api.beans.vo;

import lombok.Data;

/**
 * @author 王嘉炀
 * @date 2020/7/13 下午1:29
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
