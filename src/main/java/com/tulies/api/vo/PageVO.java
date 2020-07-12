package com.tulies.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 王嘉炀
 * @date 2018/8/19 上午10:44
 */

@Data
public class PageVO<T> {

    private List<T> list;
    private Integer pageNum;
    private Integer pageSize;
    private Long total;

}
