package com.tulies.blog.vo;

import lombok.Data;

/**
 * @author 王嘉炀
 * @date 2019/8/19 下午1:29
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
