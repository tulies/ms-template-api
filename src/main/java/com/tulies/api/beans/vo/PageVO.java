package com.tulies.api.beans.vo;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 王嘉炀
 * @date 2020/7/13 下午1:29
 */

@Data
public class PageVO<T> {

    private List<T> list;
    private Integer pageNum;
    private Integer pageSize;
    private Long total;

    public static PageVO convert(Page page){
        PageVO pageVO = new PageVO();
        pageVO.setPageNum(page.getPageable().getPageNumber()+1);
        pageVO.setPageSize(page.getPageable().getPageSize());
        pageVO.setTotal(page.getTotalElements());
        pageVO.setList(page.getContent());
        return pageVO;
    }
}
